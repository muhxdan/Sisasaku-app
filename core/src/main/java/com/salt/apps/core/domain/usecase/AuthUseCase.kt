package com.salt.apps.core.domain.usecase

import com.salt.apps.core.data.State
import com.salt.apps.core.domain.model.User
import com.salt.apps.core.domain.repository.IAuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: IAuthRepository) {
    fun signinWithGoogle(
        result: NativeSignInResult,
        client: SupabaseClient
    ): Flow<State<User>> =
        authRepository.signInWithGoogle(result, client)

    fun logout(
        client: SupabaseClient
    ): Flow<State<Unit>> = authRepository.logout(client)
}