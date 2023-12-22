//package com.farid.rijksmuseumdemo
//
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithTag
//import androidx.compose.ui.test.onNodeWithText
//import com.farid.rijksmuseumdemo.domain.model.detail.ArtObjectDetail
//import com.farid.rijksmuseumdemo.domain.model.detail.WebImageDetail
//import com.farid.rijksmuseumdemo.presentation.detail.CardItem
//import dagger.hilt.android.testing.HiltAndroidTest
//import org.junit.Rule
//import org.junit.Test
//
//@HiltAndroidTest
//class ArtDetailTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun check_state_of_detail_screen_isLoading() {
//        composeTestRule.setContent {
//            CardItem(artObject)
//        }
//
//        composeTestRule.onNodeWithTag("title").assertExists()
//        composeTestRule.onNodeWithText("Bruno").assertExists()
//        composeTestRule.onNodeWithText("I am a description. Could you test that for me")
//            .assertExists()
//    }
//}
//
//val artObject = ArtObjectDetail(
//    "I am a description. Could you test that for me",
//    true,
//    "",
//    "",
//    "",
//    "",
//    true,
//    "",
//    "Bruno",
//    WebImageDetail(
//        "",
//        120,
//        20,
//        20,
//        "",
//        0
//    )
//
//)