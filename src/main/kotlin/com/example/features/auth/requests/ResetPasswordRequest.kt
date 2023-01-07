package com.example.features.auth.requests

import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequest(
    val oldPassword: String,
    val newPassword: String,
    val confirmPassword: String
)

fun RequestValidationConfig.resetPasswordRequestValidator() {
    validate<ResetPasswordRequest> { body ->
        when {
            body.oldPassword.isEmpty() -> ValidationResult.Invalid("old password should not be empty")
            body.oldPassword.length < 4 -> ValidationResult.Invalid("old password must be at least 4 characters")

            body.newPassword.isEmpty() -> ValidationResult.Invalid("new password should not be empty")
            body.newPassword.length < 4 -> ValidationResult.Invalid("new password must be at least 4 characters")

            body.confirmPassword.isEmpty() -> ValidationResult.Invalid("confirmed password should not be empty")
            body.confirmPassword.length < 4 -> ValidationResult.Invalid("confirmed password must be at least 4 characters")

            else -> ValidationResult.Valid
        }
    }
}
