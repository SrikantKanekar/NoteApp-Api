package com.example.features.notes.requests

import com.example.model.Note
import kotlinx.serialization.Serializable

@Serializable
data class NoteInsertOrUpdateRequest(
    val notes: List<Note>
) {
//    init {
//        Validation {
//            NoteInsertOrUpdateRequest::notes required {}
//        }.validateAndThrowOnFailure(this)
//    }
}