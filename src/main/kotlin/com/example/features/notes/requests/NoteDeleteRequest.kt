package com.example.features.notes.requests

import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable
data class NoteDeleteRequest(
    val noteIds: List<String>
)

fun RequestValidationConfig.noteDeleteRequestValidator() {
    validate<NoteDeleteRequest> { body ->
        when {
            body.noteIds.isEmpty() -> ValidationResult.Invalid("note ids should not be empty")
            else -> ValidationResult.Valid
        }
    }
}