package com.salt.apps.core.domain.usecase

import com.salt.apps.core.data.State
import com.salt.apps.core.domain.model.User
import com.salt.apps.core.domain.repository.IRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCase @Inject constructor(private val iRepository: IRepository) {
    fun signinWithGoogle(
        result: NativeSignInResult,
        client: SupabaseClient
    ): Flow<State<User>> =
        iRepository.signInWithGoogle(result, client)

    fun logout(
        client: SupabaseClient
    ): Flow<State<Unit>> = iRepository.logout(client)

    fun getProfile(
    ): Flow<State<User>> = iRepository.getProfile()
}