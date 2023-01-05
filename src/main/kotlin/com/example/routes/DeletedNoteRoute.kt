package com.example.routes

import com.example.data.database.deleteDeletedNote
import com.example.data.database.getDeletedNotes
import com.example.data.database.insertDeletedNote
import com.example.data.database.insertDeletedNotes
import com.example.data.model.Note
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deletedNoteRoute() {

    route("/insert-deleted-note") {
        authenticate {
            post("{id}") {
                val email = call.principal<UserIdPrincipal>()!!.name
                val id = call.parameters["id"] ?: return@post call.respondText(
                    "note id not received",
                    status = HttpStatusCode.BadRequest
                )
                val result = insertDeletedNote(email, id)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }

    route("/insert-deleted-notes") {
        authenticate {
            post {
                val email = call.principal<UserIdPrincipal>()!!.name
                val notes = try {
                    call.receive<List<Note>>()
                } catch (e: ContentTransformationException) {
                    call.respond(HttpStatusCode.BadRequest, "Json Error")
                    return@post
                }
                val result = insertDeletedNotes(email, notes)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }

    route("/get-deleted-notes") {
        authenticate {
            get {
                val email = call.principal<UserIdPrincipal>()!!.name
                val result = getDeletedNotes(email)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }

    route("/delete-deleted-note") {
        authenticate {
            delete("{id}") {
                val email = call.principal<UserIdPrincipal>()!!.name
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "note id not received",
                    status = HttpStatusCode.BadRequest
                )
                val result = deleteDeletedNote(email, id)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }
}