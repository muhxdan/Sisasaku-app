package com.salt.apps.sisasaku.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salt.apps.sisasaku.ui.navigation.AppNavHost
import com.salt.apps.sisasaku.ui.theme.SisasakuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            !mainViewModel.splashScreenClosed.value
        }
        super.onCreate(savedInstanceState)
        setContent {
            SisasakuTheme {
                AppNavHost(mainViewModel)
            }
        }
    }
}

