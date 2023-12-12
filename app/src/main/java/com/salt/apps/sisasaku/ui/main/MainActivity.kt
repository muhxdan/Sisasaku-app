package com.salt.apps.sisasaku.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salt.apps.sisasaku.ui.navigation.SsNavHost
import com.salt.apps.sisasaku.ui.theme.SisasakuTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            !mainViewModel.splashScreenClosed.value
        }
        super.onCreate(savedInstanceState)
        setContent {
            val accessToken by mainViewModel.accessToken.collectAsState()

            LaunchedEffect(key1 = Unit) {
                delay(1000)
                mainViewModel.splashScreenClosed.value = true
            }
            SisasakuTheme {
                if (accessToken != null) {
                    SsApp()
                } else {
                    SsNavHost()
                }
            }
        }
    }
}