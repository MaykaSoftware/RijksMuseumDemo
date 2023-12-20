package com.farid.rijksmuseumdemo.route

import com.farid.rijksmuseumdemo.route.ConstantAppScreenName.ID_ARGUMENT

sealed class AppScreen(val route: String) {
    data object ArtObjectScreen : AppScreen(route = ConstantAppScreenName.HOME_SCREEN)
    data object DetailsScreen : AppScreen(route = ConstantAppScreenName.DETAILS_SCREEN) {
        fun passId(objectNumber: String): String {
            return this.route.replace(oldValue = "{$ID_ARGUMENT}", newValue = objectNumber)
        }
    }
}

object ConstantAppScreenName {
    const val ID_ARGUMENT = "objectNumber"
    const val HOME_SCREEN = "home_screen"
    const val DETAILS_SCREEN = "detail_screen/{$ID_ARGUMENT}"
}