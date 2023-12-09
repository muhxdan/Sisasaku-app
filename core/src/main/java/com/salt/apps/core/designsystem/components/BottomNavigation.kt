package com.salt.apps.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.salt.apps.core.designsystem.components.SsNavigationDefaults.navigationContainerColor
import com.salt.apps.core.designsystem.components.SsNavigationDefaults.navigationContentColor
import com.salt.apps.core.designsystem.components.SsNavigationDefaults.navigationIndicatorColor
import com.salt.apps.core.designsystem.components.SsNavigationDefaults.navigationSelectedItemColor

@Composable
fun RowScope.SsNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                contentAlignment = Alignment.Center
            ) {
                if (selected) {
                    selectedIcon()
                } else {
                    icon()
                }
            }
        },
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = navigationSelectedItemColor(),
            unselectedIconColor = navigationContentColor(),
            selectedTextColor = navigationSelectedItemColor(),
            unselectedTextColor = navigationContentColor(),
            indicatorColor = navigationIndicatorColor()
        )
    )
}

@Composable
fun SsNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = navigationContainerColor,
        contentColor = navigationContentColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

object SsNavigationDefaults {
    val navigationContainerColor = Color.Transparent

    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.background
}