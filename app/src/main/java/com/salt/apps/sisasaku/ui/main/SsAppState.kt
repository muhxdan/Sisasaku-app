package com.salt.apps.sisasaku.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.salt.apps.feature.analytics.navigation.navigateToAnalytics
import com.salt.apps.feature.home.navigation.navigateToHome
import com.salt.apps.feature.profile.navigation.navigateToProfile
import com.salt.apps.feature.wallet.navigation.navigateToWallet
import com.salt.apps.sisasaku.ui.navigation.MainDestination

@Composable
fun rememberSsAppState(
    navController: NavHostController = rememberNavController()
): SsAppState {
    return remember(navController) {
        SsAppState(navController)
    }
}

@Stable
class SsAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val mainDestinations: List<MainDestination> = MainDestination.entries

    fun navigateToTopLevelDestination(mainDestination: MainDestination) {
        trace("Navigation: ${mainDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (mainDestination) {
                MainDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                MainDestination.WALLET -> navController.navigateToWallet(topLevelNavOptions)
                MainDestination.ANALYTICS -> navController.navigateToAnalytics(topLevelNavOptions)
                MainDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
            }
        }
    }
}