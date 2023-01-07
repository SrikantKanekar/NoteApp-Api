package com.example.setup

import com.example.features.auth.routes.registerAuthRoutes
import com.example.features.notes.routes.registerNoteRoutes
import com.example.features.util.registerHomeRoute
import com.example.features.util.registerStatusRoutes
import io.ktor.server.application.*

fun Application.routesSetup() {
    registerHomeRoute()
    registerAuthRoutes()
    registerNoteRoutes()
    registerStatusRoutes()
}