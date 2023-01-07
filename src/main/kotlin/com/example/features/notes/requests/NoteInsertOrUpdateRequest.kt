package com.example.features.notes.requests

import com.example.model.Note
import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable
data class NoteInsertOrUpdateRequest(
    val notes: List<Note>
)

fun RequestValidationConfig.noteInsertOrUpdateRequestValidator() {
    validate<NoteInsertOrUpdateRequest> { body ->
        when {
            body.notes.isEmpty() -> ValidationResult.Invalid("notes should not be empty")
            else -> ValidationResult.Valid
        }
    }
}