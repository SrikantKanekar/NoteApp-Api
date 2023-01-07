package com.example.features.notes.routes

import com.example.features.notes.data.NoteRepository
import com.example.features.notes.requests.NoteInsertOrUpdateRequest
import com.example.model.UserPrincipal
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.notesInsertOrUpdateRoute(noteRepository: NoteRepository) {
    post {
        val email = call.principal<UserPrincipal>()!!.email
        val body = call.receive<NoteInsertOrUpdateRequest>()
        noteRepository.insertOrUpdateNotes(email, body.notes)
        call.respond(HttpStatusCode.OK)
    }
}