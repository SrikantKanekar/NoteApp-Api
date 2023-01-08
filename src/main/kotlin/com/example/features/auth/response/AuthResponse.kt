package com.example.features.auth.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val email: String,
    val token: String,
    val username: String,
    val isAdmin: Boolean
)