package com.salt.apps.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.salt.apps.feature.profile.ProfileScreen

const val profileNavigationRoute = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(profileNavigationRoute, navOptions)
}

fun NavGraphBuilder.profileGraph() {
    composable(
        route = profileNavigationRoute
    ) {
        ProfileScreen()
    }
}