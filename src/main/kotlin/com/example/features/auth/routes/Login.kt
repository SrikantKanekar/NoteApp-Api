package com.example.features.auth.routes

import com.example.config.JWTConfig
import com.example.features.auth.data.AuthRepository
import com.example.features.auth.requests.LoginRequest
import com.example.setup.generateJwtToken
import com.example.util.EMAIL_PASSWORD_INCORRECT
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.loginRoute(authRepository: AuthRepository, jwt: JWTConfig) {
    post("/login") {
        val (email, password) = call.receive<LoginRequest>()

        val isPasswordCorrect = authRepository.login(email, password)
        if (isPasswordCorrect) {
            val user = authRepository.getUser(email)
            val token = generateJwtToken(jwt, user)
            call.respond(token)
        } else {
            call.respond(HttpStatusCode.BadRequest, EMAIL_PASSWORD_INCORRECT)
        }
    }
}