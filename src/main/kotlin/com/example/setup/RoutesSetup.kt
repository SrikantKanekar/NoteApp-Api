package com.example.setup

import com.example.features.auth.routes.registerAuthRoutes
import com.example.features.notes.routes.noteRoute
import com.example.features.util.routes.registerHomeRoute
import com.example.features.util.routes.registerStatusRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.routesSetup() {

    registerHomeRoute()
    registerAuthRoutes()
    registerStatusRoutes()

    routing {
        noteRoute()
    }
}