package com.salt.apps.core.data

import com.salt.apps.core.data.source.remote.response.UserResponse
import com.salt.apps.core.domain.repository.IAuthRepository
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val client: SupabaseClient,
) : IAuthRepository {
    override fun signInWithGoogle(
        result: NativeSignInResult,
        client: SupabaseClient
    ): Flow<State<UserResponse>> = flow {
        try {
            emit(State.Loading())
            when (result) {
                is NativeSignInResult.Success -> {

                    val moshi = Moshi.Builder()
                        .addLast(KotlinJsonAdapterFactory())
                        .build()

                    val adapter = moshi.adapter(UserResponse::class.java)
                    val user = client.gotrue.currentUserOrNull()?.userMetadata
                    val response = user?.let {
                        try {
                            adapter.fromJson(it.toString())
                        } catch (e: JsonDataException) {
                            null
                        }
                    }

                    if (response != null) {
                        emit(State.Success(response))
                    } else {
                        emit(State.Error("User data not found or invalid JSON", null))
                    }
                }

                is NativeSignInResult.Error -> {
                    val message = result.message
                    emit(State.Error(message, null))
                }

                is NativeSignInResult.ClosedByUser -> {
                    emit(State.Error("Sign-in closed by user", null))
                }

                is NativeSignInResult.NetworkError -> {
                    val message = result.message
                    emit(State.Error(message, null))
                }
            }
            delay(300)
            emit(State.Empty)
        } catch (e: Exception) {
            emit(State.Error("Unexpected error: ${e.message}", null))
        }
    }
}
