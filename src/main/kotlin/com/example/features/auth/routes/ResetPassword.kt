package com.example.features.auth.routes

import com.example.features.auth.data.AuthRepository
import com.example.features.auth.requests.ResetPasswordRequest
import com.example.model.UserPrincipal
import com.example.util.INCORRECT_PASSWORD
import com.example.util.PASSWORD_DO_NOT_MATCH
import com.example.util.checkPassword
import com.example.util.generateHash
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.resetPassword(authRepository: AuthRepository) {
    put("/reset-password") {
        val (oldPassword, newPassword, confirmPassword) = call.receive<ResetPasswordRequest>()

        if (newPassword == confirmPassword) {
            val principal = call.principal<UserPrincipal>()!!
            val user = authRepository.getUser(principal.email)
            val isPasswordCorrect = checkPassword(oldPassword, user.password)

            if (isPasswordCorrect) {
                user.password = generateHash(newPassword)
                authRepository.updateUser(user)
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.BadRequest, INCORRECT_PASSWORD)
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, PASSWORD_DO_NOT_MATCH)
        }
    }
}
