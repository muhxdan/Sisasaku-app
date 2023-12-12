package com.salt.apps.sisasaku.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> get() = _accessToken

    val splashScreenClosed = MutableStateFlow(false)

    init {
        loadSession()
    }

    private fun loadSession() {
        viewModelScope.launch {
            supabaseClient.gotrue.sessionManager.loadSession()?.let {
                _accessToken.value = it.accessToken
            }
        }
    }
}