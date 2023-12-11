package com.salt.apps.core.domain.repository

import com.salt.apps.core.data.State
import com.salt.apps.core.data.source.remote.response.UserResponse
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun signInWithGoogle(
        result: NativeSignInResult,
        client: SupabaseClient
    ): Flow<State<UserResponse>>
}