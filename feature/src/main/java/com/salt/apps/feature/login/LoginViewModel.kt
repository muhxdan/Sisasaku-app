package com.salt.apps.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salt.apps.core.data.State
import com.salt.apps.core.data.source.remote.response.UserResponse
import com.salt.apps.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val supabaseClient: SupabaseClient,
) : ViewModel() {
    private val _loginState: MutableStateFlow<State<UserResponse>> = MutableStateFlow(State.Empty)
    val loginState: StateFlow<State<UserResponse>> get() = _loginState.asStateFlow()

    fun signInWithGoogle(result: NativeSignInResult) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.signinWithGoogle(result, supabaseClient)
                .collect { state ->
                    _loginState.value = state
                }
        }
    }

    fun getSupabaseClient(): SupabaseClient = supabaseClient
}