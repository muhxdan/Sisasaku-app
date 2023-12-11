package com.salt.apps.sisasaku.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salt.apps.sisasaku.ui.navigation.SsNavHost
import com.salt.apps.sisasaku.ui.theme.SisasakuTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var splashScreenClosed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            !splashScreenClosed
        }
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = Unit) {
                delay(2000)
                splashScreenClosed = true
            }
            SisasakuTheme {
                SsNavHost()
            }
        }
    }
}