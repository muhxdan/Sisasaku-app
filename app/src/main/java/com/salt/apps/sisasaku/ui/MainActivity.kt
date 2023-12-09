package com.salt.apps.sisasaku.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salt.apps.sisasaku.ui.theme.SisasakuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var appState: SsAppState
    private var keepSplashOpened = true
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashOpened
        }
        super.onCreate(savedInstanceState)
        setContent {
            appState = rememberSsAppState()
            SisasakuTheme {
                SsNavHost(
                    appState = appState,
                    onDataLoaded = {
                        keepSplashOpened = false
                    }
                )
            }
        }
    }
}