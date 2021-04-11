package com.example.routes

import com.example.data.database.checkIfUserExists
import com.example.data.database.registerUser
import com.example.data.model.User
import com.example.data.requests.AccountRequest
import com.example.data.response.SimpleResponse
import com.example.security.getHashWithSalt
import io.ktor.application.call
import io.ktor.features.ContentTransformationException
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.registerRoute() {
    route("/register") {
        post {
            val request = try {
                call.receive<AccountRequest>()
            } catch (e: ContentTransformationException) {
                call.respond(BadRequest)
                return@post
            }
            val userExists = checkIfUserExists(request.email)
            if (!userExists) {
                if (registerUser(User(request.email, getHashWithSalt(request.password)))) {
                    call.respond(
                        OK,
                        SimpleResponse(
                            successful = true,
                            message = "Successfully created account!"
                        )
                    )
                } else {
                    call.respond(
                        OK,
                        SimpleResponse(
                            successful = false,
                            message = "An unknown error occurred"
                        )
                    )
                }
            } else {
                call.respond(
                    OK,
                    SimpleResponse(
                        successful = false,
                        message = "A user with that E-Mail already exists"
                    )
                )
            }
        }
    }
}