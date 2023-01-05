package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponse(
    val successful: Boolean,
    val message: String
)