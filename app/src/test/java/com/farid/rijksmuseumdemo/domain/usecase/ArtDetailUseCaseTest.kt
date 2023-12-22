//package com.farid.rijksmuseumdemo.domain.usecase
//
//import app.cash.turbine.test
//import com.google.common.truth.Truth.assertThat
//import com.farid.rijksmuseumdemo.domain.repository.FakeFailureArtRepository
//import com.farid.rijksmuseumdemo.domain.repository.FakeSuccessArtRepository
//import com.feature.art_details.domain.model.ArtObjectDetail
//import com.feature.art_details.domain.model.WebImageDetail
//import com.feature.art_details.domain.use_cases.ArtDetailUseCase
//import com.feature.art_details.domain.use_cases.ERROR
//import com.feature.art_details.domain.use_cases.Resource
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.TestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.rules.TestWatcher
//import org.junit.runner.Description
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class TestCoroutineRule constructor(
//    private val testDispatcher: TestDispatcher = StandardTestDispatcher(),
//) : TestWatcher() {
//
//    override fun starting(description: Description) {
//        super.starting(description)
//        Dispatchers.setMain(testDispatcher)
//        testDispatcher.scheduler.advanceTimeBy(1000)
//    }
//
//    override fun finished(description: Description) {
//        super.finished(description)
//        Dispatchers.resetMain()
//    }
//}
//
//@RunWith(JUnit4::class)
//class ArtDetailUseCaseTest{
//    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()
//
//    @Before
//    fun setUp() {
//
//    }
//
//
//    @Test
//    fun `should return success when network request succeeds`() = runTest {
//        val fakeSuccessArtRepository  = FakeSuccessArtRepository()
//        val artSuccessDetailUseCase = ArtDetailUseCase(fakeSuccessArtRepository)
//        val testFlow = artSuccessDetailUseCase.invoke("SK-C-216")
//        testFlow.test {
//            val event = awaitItem()
//            assertThat(event).isEqualTo(Resource.Loading)
//            val second = awaitItem()
//            assertThat(second).isEqualTo(Resource.Success(dummyObject()))
//            awaitComplete()
//        }
//    }
//
//    @Test
//    fun `should return success when network request succeeds using flow`() = runTest {
//        val fakeSuccessArtRepository  = FakeSuccessArtRepository()
//        val artSuccessDetailUseCase = ArtDetailUseCase(fakeSuccessArtRepository)
//        val testFlow = artSuccessDetailUseCase.getArtObjectDetailFlow("SK-C-216")
//        testFlow.test {
//            val event = awaitItem()
//            assertThat(event).isEqualTo(Resource.Loading)
//            val second = awaitItem()
//            assertThat(second).isEqualTo(Resource.Success(dummyObject()))
//            awaitComplete()
//        }
//    }
//
//    @Test
//    fun `should return Empty field when object number is empty`() = runTest {
//        val fakeSuccessArtRepository  = FakeSuccessArtRepository()
//        val artSuccessDetailUseCase = ArtDetailUseCase(fakeSuccessArtRepository)
//        val testFlow = artSuccessDetailUseCase.invoke("")
//        testFlow.test {
//            val event = awaitItem()
//            assertThat(event).isEqualTo(Resource.Error(ERROR.EMPTY_FIELD_EXCEPTION))
//            awaitComplete()
//        }
//    }
//
//    @Test
//    fun `should return error when network request fails using flow`() = runTest{
//        val fakeFailureArtRepository = FakeFailureArtRepository()
//        val artFailureDetailUseCase = ArtDetailUseCase(fakeFailureArtRepository)
//        val testFlow = artFailureDetailUseCase.invoke("SK-C-216")
//        testFlow.test {
//            val event = awaitItem()
//            assertThat(event).isEqualTo(Resource.Loading)
//            val second = awaitItem()
//            assertThat(second).isEqualTo(Resource.Error(ERROR.HTTP_ERROR))
//            awaitComplete()
//        }
//    }
//
//    @Test
//    fun `should return error when network request fails`() = runTest{
//        val fakeFailureArtRepository = FakeFailureArtRepository()
//        val artFailureDetailUseCase = ArtDetailUseCase(fakeFailureArtRepository)
//        val testFlow = artFailureDetailUseCase.getArtObjectDetailFlow("SK-C-216")
//        testFlow.test {
//            val event = awaitItem()
//            assertThat(event).isEqualTo(Resource.Loading)
//            val second = awaitItem()
//            assertThat(second).isEqualTo(Resource.Error(ERROR.HTTP_ERROR))
//            awaitComplete()
//        }
//    }
//}
//
//fun dummyObject(): ArtObjectDetail {
//    return ArtObjectDetail(
//        description = "",
//        hasImage = true,
//        objectNumber = "",
//        id = "",
//        language = "",
//        longTitle = "",
//        showImage = true,
//        subTitle = "",
//        title = "",
//        webImage = WebImageDetail(
//            guid = "",
//            height = 120,
//            offsetPercentageX = 20,
//            offsetPercentageY = 20,
//            url = "",
//            width = 0
//        )
//    )
//}