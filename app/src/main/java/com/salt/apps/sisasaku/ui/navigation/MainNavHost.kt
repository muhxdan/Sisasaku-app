package com.salt.apps.sisasaku.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.salt.apps.feature.analytics.navigation.analyticsGraph
import com.salt.apps.feature.home.navigation.homeGraph
import com.salt.apps.feature.home.navigation.homeNavigationRoute
import com.salt.apps.feature.profile.navigation.navigateToLogin
import com.salt.apps.feature.profile.navigation.profileGraph
import com.salt.apps.feature.wallet.navigation.walletGraph

@Composable
fun MainNavHost(
    navController: NavHostController,
    mainNavController: NavHostController,
    startDestination: String = homeNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        homeGraph()
        walletGraph()
        analyticsGraph()
        profileGraph(onLogoutClick = mainNavController::navigateToLogin)
    }
}
