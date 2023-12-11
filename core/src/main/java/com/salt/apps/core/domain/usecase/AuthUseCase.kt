package com.salt.apps.core.domain.usecase

import com.salt.apps.core.data.State
import com.salt.apps.core.data.source.remote.response.UserResponse
import com.salt.apps.core.domain.repository.IAuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: IAuthRepository) {
    fun signinWithGoogle(
        result: NativeSignInResult,
        client: SupabaseClient
    ): Flow<State<UserResponse>> =
        authRepository.signInWithGoogle(result, client)
}