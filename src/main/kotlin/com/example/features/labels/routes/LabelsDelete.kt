package com.example.features.labels.routes

import com.example.features.labels.data.LabelRepository
import com.example.features.labels.requests.LabelDeleteRequest
import com.example.model.UserPrincipal
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.labelsDeleteRoute(labelRepository: LabelRepository) {
    delete {
        val body = call.receive<LabelDeleteRequest>()
        val email = call.principal<UserPrincipal>()!!.email
        labelRepository.deleteLabels(email, body.labelIds)
        call.respond(HttpStatusCode.OK)
    }
}