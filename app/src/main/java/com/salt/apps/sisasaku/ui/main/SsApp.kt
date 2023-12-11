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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.salt.apps.core.designsystem.components.SsNavigationBar
import com.salt.apps.core.designsystem.components.SsNavigationBarItem
import com.salt.apps.sisasaku.ui.navigation.MainDestination
import com.salt.apps.sisasaku.ui.navigation.SsMainNavHost

@Composable
fun SsApp(
    appState: SsAppState = rememberSsAppState(),
) {
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            SsBottomBar(
                destinations = appState.mainDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination,
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
            SsMainNavHost(
                appState = appState
            )
        }
    }
}

@Composable
private fun SsBottomBar(
    destinations: List<MainDestination>,
    onNavigateToDestination: (MainDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    SsNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            SsNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
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