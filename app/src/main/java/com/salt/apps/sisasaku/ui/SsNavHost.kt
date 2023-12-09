package com.salt.apps.sisasaku.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.salt.apps.feature.analytics.navigation.analyticsGraph
import com.salt.apps.feature.home.navigation.homeGraph
import com.salt.apps.feature.home.navigation.homeNavigationRoute
import com.salt.apps.feature.login.navigation.loginGraph
import com.salt.apps.feature.login.navigation.loginNavigationRoute
import com.salt.apps.feature.profile.navigation.profileGraph
import com.salt.apps.feature.wallet.navigation.walletGraph
import com.salt.apps.sisasaku.ui.navigation.mainGraph
import com.salt.apps.sisasaku.ui.navigation.navigateToMain

@Composable
fun SsNavHost(
    appState: SsAppState,
    onDataLoaded: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = loginNavigationRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        loginGraph(
            onLoginClick = navController::navigateToMain,
            onDataLoaded = onDataLoaded
        )
        mainGraph()
    }
}

@Composable
fun SsMainNavHost(
    appState: SsAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph()
        walletGraph()
        analyticsGraph()
        profileGraph()
    }
}
