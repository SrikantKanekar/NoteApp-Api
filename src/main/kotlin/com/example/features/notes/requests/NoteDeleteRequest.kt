package com.example.features.notes.requests

import com.example.util.validateAndThrowOnFailure
import io.konform.validation.Validation
import kotlinx.serialization.Serializable

@Serializable
data class NoteDeleteRequest(
    val noteIds: List<String>
) {
    init {
        Validation {
            NoteDeleteRequest::noteIds required {}
        }.validateAndThrowOnFailure(this)
    }
}