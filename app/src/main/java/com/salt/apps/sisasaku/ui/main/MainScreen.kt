package com.salt.apps.sisasaku.ui.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.salt.apps.core.designsystem.components.BottomNavigationBar
import com.salt.apps.core.designsystem.components.BottomNavigationBarItem
import com.salt.apps.feature.analytics.navigation.navigateToAnalytics
import com.salt.apps.feature.home.navigation.navigateToHome
import com.salt.apps.feature.profile.navigation.navigateToProfile
import com.salt.apps.feature.wallet.navigation.navigateToWallet
import com.salt.apps.sisasaku.ui.navigation.MainNavHost
import com.salt.apps.sisasaku.ui.navigation.destination.MainDestination

@Composable
fun MainScreen(
    mainNavController: NavHostController,
) {
    val mainDestinations: List<MainDestination> = MainDestination.entries
    val navController = rememberNavController()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            BottomBar(
                navController = navController,
                destinations = mainDestinations,
            )
        }
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            MainNavHost(navController = navController, mainNavController = mainNavController)
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    destinations: List<MainDestination>,
) {
    BottomNavigationBar {
        val currentDestination = navController
            .currentBackStackEntryAsState().value?.destination

        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            BottomNavigationBarItem(
                selected = selected,
                onClick = { navigateToTopLevelDestination(navController, destination) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.unselectedIcon),
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        painterResource(id = destination.selectedIcon),
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) },
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: MainDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

private fun navigateToTopLevelDestination(
    navController: NavHostController,
    mainDestination: MainDestination
) {
    val topLevelNavOptions = navOptions {
        popUpTo(navController.graph.startDestinationId) {
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