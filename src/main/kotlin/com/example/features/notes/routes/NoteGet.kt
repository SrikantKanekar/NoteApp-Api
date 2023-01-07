package com.example.features.notes.routes

import com.example.features.notes.data.NoteRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.noteGetRoute(noteRepository: NoteRepository) {
    get("{id}") {
        val id = call.parameters["id"] ?: return@get call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Missing or malformed id"
        )
        val email = call.principal<UserIdPrincipal>()!!.name

        when (val note = noteRepository.getNoteById(email, id)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(note)
        }
    }
}