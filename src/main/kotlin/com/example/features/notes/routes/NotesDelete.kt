package com.example.features.notes.routes

import com.example.features.notes.data.NoteRepository
import com.example.features.notes.requests.NoteDeleteRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.notesDeleteRoute(noteRepository: NoteRepository) {
    delete {
        val body = call.receive<NoteDeleteRequest>()
        val email = call.principal<UserIdPrincipal>()!!.name
        noteRepository.deleteNotes(email, body.noteIds)
        call.respond(HttpStatusCode.OK)
    }
}