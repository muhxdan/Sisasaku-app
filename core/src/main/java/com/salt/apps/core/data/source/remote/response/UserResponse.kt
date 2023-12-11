package com.salt.apps.core.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val email: String,
    @Json(name = "email_verified")
    val emailVerified: Boolean,
    @Json(name = "full_name")
    val fullName: String,
    val iss: String,
    val name: String,
    @Json(name = "phone_verified")
    val phoneVerified: Boolean,
    val picture: String,
    @Json(name = "provider_id")
    val providerId: String,
    val sub: String
)