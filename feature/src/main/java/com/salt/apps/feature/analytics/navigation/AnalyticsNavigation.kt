package com.salt.apps.feature.analytics.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.salt.apps.feature.analytics.AnalyticsScreen

const val analyticsNavigationRoute = "analytics_route"

fun NavController.navigateToAnalytics(navOptions: NavOptions? = null) {
    this.navigate(analyticsNavigationRoute, navOptions)
}

fun NavGraphBuilder.analyticsGraph() {
    composable(
        route = analyticsNavigationRoute
    ) {
        AnalyticsScreen()
    }
}