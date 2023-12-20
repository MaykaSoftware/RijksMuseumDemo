package com.farid.rijksmuseumdemo.di

import android.content.Context
import androidx.paging.Pager
import androidx.room.Room
import com.farid.rijksmuseumdemo.data.local.dao.ArtObjectDao
import com.farid.rijksmuseumdemo.data.local.database.ArtDatabase
import com.farid.rijksmuseumdemo.data.local.entity.home.ArtObjectEntity
import com.farid.rijksmuseumdemo.data.remote.ArtObjectApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.farid.rijksmuseumdemo.data.remote.ArtObjectApi.Companion.API_KEY
import com.farid.rijksmuseumdemo.data.remote.ArtObjectApi.Companion.BASE_URL
import com.farid.rijksmuseumdemo.domain.repository.ArtRepository
import com.farid.rijksmuseumdemo.domain.repository.ArtRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideArtObjectDB(@ApplicationContext context: Context): ArtDatabase {
        return Room.databaseBuilder(
            context,
            ArtDatabase::class.java,
            ArtDatabase.DATABASE_NAME,
        )
            .build()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", API_KEY)
                .build()
            request.url(url)
            chain.proceed(request.build())
        })

        builder.apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return builder.build()
    }

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        val contentType = "application/json".toMediaType()
        val jsonConverter = json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(jsonConverter)
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun bindArtRepository(
        pager: Pager<Int, ArtObjectEntity>,
        artObjectApi: ArtObjectApi,
        artObjectDao: ArtObjectDao
    ): ArtRepository {
        return ArtRepositoryImpl(pager, artObjectApi, artObjectDao)
    }
}