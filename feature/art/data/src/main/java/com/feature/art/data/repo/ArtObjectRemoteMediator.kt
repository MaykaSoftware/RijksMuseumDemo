package com.feature.art.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.core.cache.dataSource.database.ArtDatabase
import com.core.cache.entity.art.ArtObjectEntity
import com.core.cache.entity.art.RemoteKeysEntity
import com.core.network.dataSource.ArtDataSource
import com.feature.art.data.mapper.toArtObjectEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class ArtObjectRemoteMediator @Inject constructor(
    private val artDatabase: ArtDatabase,
    private val artDataSource: ArtDataSource
) : RemoteMediator<Int, ArtObjectEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArtObjectEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = artDataSource.getMuseumObjects(
                language = "nl",
                page = page,
                pageCount = state.config.pageSize
            )

            val artObjects = apiResponse.artObjectResponses.map {
                it.toArtObjectEntity()
            }
            val endOfPaginationReached = artObjects.isEmpty()

            artDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    artDatabase.getRemoteKeysDao().clearRemoteKeys()
                    artDatabase.artObjectDao().deleteAll()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = artObjects.map {
                    RemoteKeysEntity(
                        artObjectID = it.id,
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey
                    )
                }

                artDatabase.getRemoteKeysDao().insertAll(remoteKeys)
                artDatabase.artObjectDao()
                    .upsertAll(artObjects.onEachIndexed { _, artObject -> artObject.page = page })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ArtObjectEntity>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                artDatabase.getRemoteKeysDao().getRemoteKeyByArtObjectID(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ArtObjectEntity>): RemoteKeysEntity? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            artDatabase.getRemoteKeysDao().getRemoteKeyByArtObjectID(movie.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ArtObjectEntity>): RemoteKeysEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            artDatabase.getRemoteKeysDao().getRemoteKeyByArtObjectID(movie.id)
        }
    }
}