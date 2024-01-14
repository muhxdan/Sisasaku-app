package com.salt.apps.sisasaku.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.salt.apps.feature.login.navigation.loginGraph
import com.salt.apps.feature.login.navigation.loginNavigationRoute
import com.salt.apps.feature.login.navigation.navigateToMain
import com.salt.apps.sisasaku.ui.main.MainViewModel
import com.salt.apps.sisasaku.ui.main.navigation.mainGraph
import io.github.jan.supabase.gotrue.SessionStatus
import kotlinx.coroutines.delay

@Composable
fun AppNavHost(
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val sessionStatus by mainViewModel.sessionStatus.collectAsState()
    val initialRoute = remember { mutableStateOf(loginNavigationRoute) }

    when (sessionStatus) {
        is SessionStatus.NotAuthenticated -> initialRoute.value = loginNavigationRoute
        is SessionStatus.Authenticated -> initialRoute.value = "main_route"
        is SessionStatus.LoadingFromStorage -> {}
        is SessionStatus.NetworkError -> {}
    }

    val navController = rememberNavController()

    LaunchedEffect(sessionStatus) {
        delay(1000)
        mainViewModel.splashScreenClosed.value = true
        mainViewModel.splashScreenActive.value = false
    }

    NavHost(navController = navController, startDestination = initialRoute.value) {
        loginGraph(onLoginClick = navController::navigateToMain)
        mainGraph(mainNavController = navController)
    }
}