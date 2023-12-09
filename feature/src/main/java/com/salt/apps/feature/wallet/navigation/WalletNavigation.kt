package com.salt.apps.feature.wallet.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.salt.apps.feature.wallet.WalletScreen

const val walletNavigationRoute = "wallet_route"

fun NavController.navigateToWallet(navOptions: NavOptions? = null) {
    this.navigate(walletNavigationRoute, navOptions)
}

fun NavGraphBuilder.walletGraph() {
    composable(
        route = walletNavigationRoute
    ) {
        WalletScreen()
    }
}