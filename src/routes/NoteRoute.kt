package com.example.routes

import com.example.data.database.*
import com.example.data.model.Note
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.noteRoute() {
    route("/insert-or-update-note") {
        authenticate {
            post {
                val email = call.principal<UserIdPrincipal>()!!.name
                val note = try {
                    call.receive<Note>()
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Json Error")
                    return@post
                }
                val result = insertOrUpdateNote(email, note)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }

    route("/insert-or-update-notes") {
        authenticate {
            post {
                val email = call.principal<UserIdPrincipal>()!!.name
                val notes = try {
                    call.receive<List<Note>>()
                } catch (e: ContentTransformationException) {
                    call.respond(HttpStatusCode.BadRequest, "Json Error")
                    return@post
                }
                val result = insertOrUpdateNotes(email, notes)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }

    route("/get-note") {
        authenticate {
            get("{id}") {
                val email = call.principal<UserIdPrincipal>()!!.name
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "note id not received",
                    status = HttpStatusCode.BadRequest
                )
                val result = getNote(email, id)
                call.respond(HttpStatusCode.OK, result ?: "Note not found")
            }
        }
    }

    route("/get-all-notes") {
        authenticate {
            get {
                val email = call.principal<UserIdPrincipal>()!!.name
                val result = getAllNotes(email)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }

    route("/delete-note") {
        authenticate {
            delete("{id}") {
                val email = call.principal<UserIdPrincipal>()!!.name
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "note id not received",
                    status = HttpStatusCode.BadRequest
                )
                val result = deleteNote(email, id)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }

    route("/delete-all-notes") {
        authenticate {
            delete {
                val email = call.principal<UserIdPrincipal>()!!.name
                val result = deleteAllNotes(email)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }
}