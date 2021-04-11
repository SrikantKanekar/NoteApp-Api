package com.example.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class AccountRequest(
    val email: String,
    val password: String
)