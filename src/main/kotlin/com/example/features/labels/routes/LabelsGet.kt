package com.example.features.labels.routes

import com.example.features.labels.data.LabelRepository
import com.example.model.UserPrincipal
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.labelsGetRoute(labelRepository: LabelRepository) {
    get {
        val email = call.principal<UserPrincipal>()!!.email
        val labels = labelRepository.getAllLabels(email)
        call.respond(labels)
    }
}