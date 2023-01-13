package com.example.features.labels.requests

import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable
data class LabelDeleteRequest(
    val labelIds: List<String>
)

fun RequestValidationConfig.labelDeleteRequestValidator() {
    validate<LabelDeleteRequest> { body ->
        when {
            body.labelIds.isEmpty() -> ValidationResult.Invalid("label ids should not be empty")
            else -> ValidationResult.Valid
        }
    }
}