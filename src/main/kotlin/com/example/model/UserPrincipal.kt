package com.example.model

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserPrincipal(
    val email: String,
    val username: String,
    val isAdmin: Boolean
) : Principal