package com.example.setup

import com.example.features.auth.requests.loginRequestValidator
import com.example.features.auth.requests.registerRequestValidator
import com.example.features.auth.requests.resetPasswordRequestValidator
import com.example.features.notes.requests.noteDeleteRequestValidator
import com.example.features.notes.requests.noteInsertOrUpdateRequestValidator
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.requestValidationSetup() {
    install(RequestValidation) {
        loginRequestValidator()
        registerRequestValidator()
        resetPasswordRequestValidator()
        noteInsertOrUpdateRequestValidator()
        noteDeleteRequestValidator()
    }
}