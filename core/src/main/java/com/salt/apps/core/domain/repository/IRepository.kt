package com.salt.apps.core.domain.repository

import com.salt.apps.core.data.State
import com.salt.apps.core.domain.model.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun signInWithGoogle(
        result: NativeSignInResult,
        client: SupabaseClient
    ): Flow<State<User>>

    fun logout(
        client: SupabaseClient
    ): Flow<State<Unit>>

    fun getProfile(
    ): Flow<State<User>>
}