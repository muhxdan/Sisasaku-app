package com.salt.apps.sisasaku.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    private val _sessionStatus = MutableStateFlow<SessionStatus>(SessionStatus.LoadingFromStorage)
    val sessionStatus: StateFlow<SessionStatus> get() = _sessionStatus

    val splashScreenActive = MutableStateFlow(true)
    val splashScreenClosed = MutableStateFlow(false)

    init {
        observeSessionStatus()
    }

    private fun observeSessionStatus() {
        viewModelScope.launch {
            supabaseClient.gotrue.sessionStatus.collect { status ->
                if (splashScreenActive.value) {
                    _sessionStatus.value = status
                }
            }
        }
    }
}