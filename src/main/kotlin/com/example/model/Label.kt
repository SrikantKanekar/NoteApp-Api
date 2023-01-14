package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Label(
    val id: String,
    val name: String,
    val createdAt: String,
    val updatedAt: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Label

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
