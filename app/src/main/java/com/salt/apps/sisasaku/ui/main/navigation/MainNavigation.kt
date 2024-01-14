package com.salt.apps.sisasaku.ui.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.salt.apps.sisasaku.ui.main.MainScreen

const val mainNavigationRoute = "main_route"
const val mainScreenRoute = "main_screen_route"

fun NavGraphBuilder.mainGraph(
    mainNavController: NavHostController
) {
    navigation(startDestination = mainScreenRoute, route = mainNavigationRoute) {
        composable(mainScreenRoute) {
            MainScreen(mainNavController = mainNavController)
        }
    }
}