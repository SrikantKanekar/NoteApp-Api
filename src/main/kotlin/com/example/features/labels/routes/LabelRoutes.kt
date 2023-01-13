package com.example.features.labels.routes

import com.example.features.labels.data.LabelRepository
import com.example.util.USER_AUTH
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.registerLabelRoutes() {

    val labelRepository by inject<LabelRepository>()

    routing {
        route("/labels") {
            authenticate(USER_AUTH) {
                labelsInsertOrUpdateRoute(labelRepository)
                labelGetRoute(labelRepository)
                labelsGetRoute(labelRepository)
                labelsDeleteRoute(labelRepository)
            }
        }
    }
}