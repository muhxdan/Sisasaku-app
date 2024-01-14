package com.salt.apps.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.salt.apps.feature.login.LoginScreen

const val loginNavigationRoute = "login_route"

fun NavController.navigateToMain() {
    this.navigate("main_route") {
        popUpTo(loginNavigationRoute) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.loginGraph(
    onLoginClick: () -> Unit,
) {
    composable(
        route = loginNavigationRoute
    ) {
        LoginScreen(onLoginClick)
    }
}