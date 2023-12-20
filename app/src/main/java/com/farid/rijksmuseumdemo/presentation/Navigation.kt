package com.farid.rijksmuseumdemo.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.farid.rijksmuseumdemo.presentation.detail.ArtDetailViewModel
import com.farid.rijksmuseumdemo.presentation.detail.ArtDetailsScreen
import com.farid.rijksmuseumdemo.presentation.detail.State
import com.farid.rijksmuseumdemo.presentation.home.ArtListScreen
import com.farid.rijksmuseumdemo.presentation.home.ArtViewModel
import com.farid.rijksmuseumdemo.route.AppScreen
import com.farid.rijksmuseumdemo.route.ConstantAppScreenName

@Composable
fun Navigation(navController: NavHostController, innerPadding: PaddingValues, onTitleChanged: (String) -> Unit) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.ArtObjectScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {

        composable(
            route = AppScreen.ArtObjectScreen.route
        ) {
            val viewModel: ArtViewModel = hiltViewModel()
            val artObjects = viewModel.artObjectFlow.collectAsLazyPagingItems()
            ArtListScreen(artObjects = artObjects, navController = navController)
            onTitleChanged(Route.ArtScreen.route)
        }

        composable(
            route = AppScreen.DetailsScreen.route,
            arguments = listOf(navArgument(ConstantAppScreenName.ID_ARGUMENT) {
                type = NavType.StringType
            })
        ) {
            val viewModel: ArtDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            ArtDetailsScreen(state)
            when(state){
                is State.Error -> {
                    onTitleChanged("ERROR")
                }
                State.Loading -> {

                }
                is State.Success -> {
                    onTitleChanged((state as State.Success).artObjectDetail?.title?: Route.ArtDetailScreen.route)
                }
            }
        }
    }
}