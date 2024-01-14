package com.salt.apps.core.data

import android.util.Log
import com.salt.apps.core.data.source.local.LocalDataSource
import com.salt.apps.core.data.source.remote.response.UserResponse
import com.salt.apps.core.domain.model.User
import com.salt.apps.core.domain.repository.IRepository
import com.salt.apps.core.utilities.DataMapper
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val client: SupabaseClient,
    private val localDataSource: LocalDataSource,
) : IRepository {
    override fun signInWithGoogle(
        result: NativeSignInResult,
        client: SupabaseClient
    ): Flow<State<User>> = flow {
        try {
            emit(State.Loading())
            when (result) {
                is NativeSignInResult.Success -> {
                    val moshi = Moshi.Builder()
                        .addLast(KotlinJsonAdapterFactory())
                        .build()

                    val adapter = moshi.adapter(UserResponse::class.java)
                    val user = client.gotrue.currentUserOrNull()?.userMetadata
                    val userId = client.gotrue.currentUserOrNull()?.id

                    val response = user?.let {
                        try {
                            adapter.fromJson(it.toString())
                        } catch (e: JsonDataException) {
                            null
                        }
                    }

                    if (response != null) {
                        val userEntity = DataMapper.mapUserResponseToEntity(
                            userId = userId ?: "N/A",
                            userResponse = response
                        )
                        localDataSource.insertUser(userEntity)

                        val userModel = DataMapper.mapUserEntityToUserModel(userEntity)
                        Log.i("loginUser", userModel.toString())
                        emit(State.Success(userModel))
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
            emit(State.Initial)
        } catch (e: Exception) {
            emit(State.Error("Unexpected error: ${e.message}", null))
        }
    }.flowOn(Dispatchers.IO)

    override fun logout(client: SupabaseClient): Flow<State<Unit>> = flow {
        try {
            emit(State.Loading())
            localDataSource.deleteUser().also {
                client.gotrue.logout()
            }
            emit(State.Success())
        } catch (e: Exception) {
            emit(State.Error("Unexpected error: ${e.message}", null))
        }
    }.flowOn(Dispatchers.IO)

    override fun getProfile(): Flow<State<User>> = flow {
        try {
            emit(State.Loading())
            val user = localDataSource.getUser()
            if (user.toString().isNotEmpty()) {
                val userModel = DataMapper.mapUserEntityToUserModel(user)
                emit(State.Success(userModel))
            } else {
                emit(State.Error("User data not found", null))
            }
        } catch (e: Exception) {
            emit(State.Error("Unexpected error: ${e.message}", null))
        }
    }.flowOn(Dispatchers.IO)
}
