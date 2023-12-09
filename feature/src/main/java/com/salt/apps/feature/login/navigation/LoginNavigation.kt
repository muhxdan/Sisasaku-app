package com.salt.apps.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.salt.apps.feature.login.LoginScreen

const val loginNavigationRoute = "login_route"

fun NavGraphBuilder.loginGraph(
    onLoginClick: () -> Unit,
    onDataLoaded: () -> Unit
) {
    composable(
        route = loginNavigationRoute
    ) {
        LoginScreen(onLoginClick, onDataLoaded)
    }
}