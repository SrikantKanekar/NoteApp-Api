package com.example.features.notes.requests

import kotlinx.serialization.Serializable

@Serializable
data class NoteDeleteRequest(
    val noteIds: List<String>
) {
//    init {
//        Validation {
//            NoteDeleteRequest::noteIds required {}
//        }.validateAndThrowOnFailure(this)
//    }
}