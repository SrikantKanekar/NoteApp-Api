package com.example.features.notes.routes

import com.example.features.notes.data.NoteRepository
import com.example.util.USER_AUTH
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.registerNoteRoutes() {

    val noteRepository by inject<NoteRepository>()

    routing {
        route("/notes") {
            authenticate(USER_AUTH) {
                notesInsertOrUpdateRoute(noteRepository)
                noteGetRoute(noteRepository)
                notesGetRoute(noteRepository)
                notesDeleteRoute(noteRepository)
            }
        }
    }
}