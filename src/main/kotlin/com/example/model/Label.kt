package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Label(
    val id: String,
    val name: String,
    val createdAt: String,
    val updatedAt: String
)
