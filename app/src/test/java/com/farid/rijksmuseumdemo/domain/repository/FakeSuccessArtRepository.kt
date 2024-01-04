//package com.farid.rijksmuseumdemo.domain.repository
//
//import androidx.paging.PagingData
//import com.farid.rijksmuseumdemo.domain.model.detail.ArtObjectDetail
//import com.farid.rijksmuseumdemo.domain.model.detail.WebImageDetail
//import com.farid.rijksmuseumdemo.domain.model.home.ArtObject
//import com.farid.rijksmuseumdemo.domain.usecase.EmptyFieldException
//import com.feature.art_details.domain.use_cases.EmptyFieldException
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//
//class FakeSuccessArtRepository: ArtRepository {
//
//    private val artObject = ArtObjectDetail(
//        "",
//        true,
//        "",
//        "",
//        "",
//        "",
//        true,
//        "",
//        "",
//        WebImageDetail(
//            "",
//            120,
//            20,
//            20,
//            "",
//            0
//        )
//
//    )
//    override val artObjectData: Flow<PagingData<ArtObject>>
//        get() = TODO("Not yet implemented")
//
//    override suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail> = flow {
//        if(objectNumber.trim().isNotEmpty()){
//            emit(artObject)
//        } else {
//            throw EmptyFieldException()
//        }
//    }
//
//    override suspend fun getArtObject(objectNumber: String): ArtObjectDetail {
//        if(objectNumber.trim().isNotEmpty()){
//            return artObject
//        } else {
//            throw EmptyFieldException()
//        }
//    }
//}