package com.example.routes

import com.example.data.database.checkPasswordForEmail
import com.example.data.requests.AccountRequest
import com.example.data.response.SimpleResponse
import io.ktor.application.call
import io.ktor.features.ContentTransformationException
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.loginRoute() {
    route("/login") {
        post {
            val request = try {
                call.receive<AccountRequest>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val isPasswordCorrect = checkPasswordForEmail(request.email, request.password)
            if (isPasswordCorrect) {
                call.respond(
                    OK,
                    SimpleResponse(
                        successful = true,
                        message = "Your are now logged in!"
                    )
                )
            } else {
                call.respond(
                    OK,
                    SimpleResponse(
                        successful = false,
                        message = "The E-Mail or password is incorrect"
                    )
                )
            }
        }
    }
}
