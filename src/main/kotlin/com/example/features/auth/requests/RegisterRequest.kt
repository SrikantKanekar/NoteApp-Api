package com.example.features.auth.requests

import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password1: String,
    val password2: String
)

fun RequestValidationConfig.registerRequestValidator() {
    validate<RegisterRequest> { body ->
        when {
            body.username.length < 2 -> ValidationResult.Invalid("username must be at least 2 characters")
            body.username.length > 50 -> ValidationResult.Invalid("username must be at most 50 characters")

            body.email.isBlank() -> ValidationResult.Invalid("email should not be empty")

            body.password1.isEmpty() -> ValidationResult.Invalid("password should not be empty")
            body.password1.length < 4 -> ValidationResult.Invalid("password must be at least 4 characters")

            body.password2.isEmpty() -> ValidationResult.Invalid("password should not be empty")
            body.password2.length < 4 -> ValidationResult.Invalid("password must be at least 4 characters")

            else -> ValidationResult.Valid
        }
    }
}