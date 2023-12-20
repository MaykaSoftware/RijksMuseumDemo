package com.farid.rijksmuseumdemo.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import com.farid.rijksmuseumdemo.presentation.common.ArtAppBar

sealed class Route(
    val route: String
){
    data object ArtScreen: Route(route = "Art Screen")
    data object ArtDetailScreen: Route(route = "Art Detail")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RijksMuseumDemoApp(
) {
    val navController = rememberNavController()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var dynamicTitle by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ArtAppBar(
                title = dynamicTitle,
                scrollBehavior,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Navigation( navController = navController, innerPadding = innerPadding,
            onTitleChanged = { newTitle -> dynamicTitle = newTitle })
    }
}