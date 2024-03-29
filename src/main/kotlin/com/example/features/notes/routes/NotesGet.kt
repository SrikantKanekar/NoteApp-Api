package com.example.features.notes.routes

import com.example.features.notes.data.NoteRepository
import com.example.model.UserPrincipal
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.notesGetRoute(noteRepository: NoteRepository) {
    get {
        val email = call.principal<UserPrincipal>()!!.email
        val notes = noteRepository.getAllNotes(email)
        call.respond(notes)
    }
}