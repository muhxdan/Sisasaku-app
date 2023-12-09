package com.salt.apps.sisasaku.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.salt.apps.sisasaku.ui.SsApp

const val mainNavigationRoute = "main_route"

fun NavController.navigateToMain() {
    this.navigate(mainNavigationRoute) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.mainGraph() {
    composable(
        route = mainNavigationRoute
    ) {
        SsApp()
    }
}