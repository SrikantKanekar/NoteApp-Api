package com.example.routes

import com.example.data.model.Note
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.testRoute() {
    route("/") {
        authenticate {
            post {
                val email = call.principal<UserIdPrincipal>()!!.name

                val note = try {
                    call.receive<Note>()
                } catch (e: ContentTransformationException) {
                    call.respond(HttpStatusCode.BadRequest, "Didn't receive Note")
                    return@post
                }
                call.respond(note)
            }
        }
    }
}