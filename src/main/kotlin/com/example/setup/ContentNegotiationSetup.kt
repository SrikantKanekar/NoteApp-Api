package com.example.setup

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.contentNegotiationSetup() {
    install(ContentNegotiation) {
        json()
    }
}