package com.example.features.auth.requests

import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    var email: String,
    var password: String
)

fun RequestValidationConfig.loginRequestValidator() {
    validate<LoginRequest> { body ->
        when {
            body.email.isBlank() -> ValidationResult.Invalid("email should not be empty")

            body.password.isEmpty() -> ValidationResult.Invalid("password should not be empty")
            body.password.length < 4 -> ValidationResult.Invalid("password must be at least 4 characters")

            else -> ValidationResult.Valid
        }
    }
}

