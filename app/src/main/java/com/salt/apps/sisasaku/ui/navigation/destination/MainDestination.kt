package com.salt.apps.sisasaku.ui.navigation.destination

import com.salt.apps.core.designsystem.icon.SsIcons
import com.salt.apps.feature.R

enum class MainDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int,
) {
    HOME(
        selectedIcon = SsIcons.Home,
        unselectedIcon = SsIcons.HomeBorder,
        iconTextId = R.string.home,
    ),
    WALLET(
        selectedIcon = SsIcons.Wallet,
        unselectedIcon = SsIcons.WalletBorder,
        iconTextId = R.string.wallet,
    ),
    ANALYTICS(
        selectedIcon = SsIcons.Analytics,
        unselectedIcon = SsIcons.AnalyticsBorder,
        iconTextId = R.string.analytics,
    ),
    PROFILE(
        selectedIcon = SsIcons.Profile,
        unselectedIcon = SsIcons.ProfileBorder,
        iconTextId = R.string.profile,
    ),
}