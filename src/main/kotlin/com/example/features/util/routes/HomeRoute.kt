package com.example.features.util.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.registerHomeRoute() {
    routing {
        route("/") {
            get {
                call.respondText("Ktor note server!")
            }
        }
    }
}