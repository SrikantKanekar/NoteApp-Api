package com.example.setup

import com.example.features.auth.requests.loginRequestValidator
import com.example.features.auth.requests.registerRequestValidator
import com.example.features.auth.requests.resetPasswordRequestValidator
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.requestValidationSetup() {
    install(RequestValidation) {
        loginRequestValidator()
        registerRequestValidator()
        resetPasswordRequestValidator()
    }
}