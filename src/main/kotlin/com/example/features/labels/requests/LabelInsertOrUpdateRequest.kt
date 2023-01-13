package com.example.features.labels.requests

import com.example.model.Label
import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable
data class LabelInsertOrUpdateRequest(
    val labels: List<Label>
)

fun RequestValidationConfig.labelInsertOrUpdateRequestValidator() {
    validate<LabelInsertOrUpdateRequest> { body ->
        when {
            body.labels.isEmpty() -> ValidationResult.Invalid("labels should not be empty")
            else -> ValidationResult.Valid
        }
    }
}