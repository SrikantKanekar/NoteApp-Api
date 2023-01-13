package com.example.features.labels.routes

import com.example.features.labels.data.LabelRepository
import com.example.features.labels.requests.LabelInsertOrUpdateRequest
import com.example.model.UserPrincipal
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.labelsInsertOrUpdateRoute(labelRepository: LabelRepository) {
    post {
        val email = call.principal<UserPrincipal>()!!.email
        val body = call.receive<LabelInsertOrUpdateRequest>()
        labelRepository.insertOrUpdateLabels(email, body.labels)
        call.respond(HttpStatusCode.OK)
    }
}