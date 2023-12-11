package com.salt.apps.sisasaku.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.salt.apps.feature.analytics.navigation.analyticsGraph
import com.salt.apps.feature.home.navigation.homeGraph
import com.salt.apps.feature.home.navigation.homeNavigationRoute
import com.salt.apps.feature.login.navigation.loginGraph
import com.salt.apps.feature.login.navigation.loginNavigationRoute
import com.salt.apps.feature.profile.navigation.profileGraph
import com.salt.apps.feature.wallet.navigation.walletGraph
import com.salt.apps.sisasaku.ui.main.SsAppState
import com.salt.apps.sisasaku.ui.main.navigation.mainGraph
import com.salt.apps.sisasaku.ui.main.navigation.navigateToMain
import com.salt.apps.sisasaku.ui.main.rememberSsAppState

@Composable
fun SsNavHost(
    appState: SsAppState = rememberSsAppState(),
    startDestination: String = loginNavigationRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        loginGraph(onLoginClick = navController::navigateToMain)
        mainGraph()
    }
}

@Composable
fun SsMainNavHost(
    appState: SsAppState,
    startDestination: String = homeNavigationRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        homeGraph()
        walletGraph()
        analyticsGraph()
        profileGraph()
    }
}
