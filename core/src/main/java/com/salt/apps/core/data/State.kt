package com.salt.apps.core.data

sealed class State<out T> {
    data class Loading<out T>(val data: T? = null) : State<T>()
    data class Success<out T>(val data: T) : State<T>()
    data class Error<out T>(val message: String, val data: T? = null) : State<T>()
    data object Empty : State<Nothing>()
}
