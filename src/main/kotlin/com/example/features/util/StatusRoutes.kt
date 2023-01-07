package com.example.features.util

import com.example.util.DatabaseException
import com.mongodb.MongoTimeoutException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException

fun Application.registerStatusRoutes() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is RequestValidationException -> {
                    call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
                }

                is BadRequestException -> {
                    call.respond(HttpStatusCode.BadRequest)
                }

                is SerializationException -> {
                    println("Serialization exception: ${cause.message}")
                    call.respond(HttpStatusCode.BadRequest, cause.message ?: "Bad request")
                }

                is MongoTimeoutException -> {
                    println("Database timeout exception")
                    call.respond(HttpStatusCode.InternalServerError)
                }

                is DatabaseException -> {
                    println("Database read write exception: ${cause.message}")
                    call.respond(HttpStatusCode.InternalServerError)
                }

                else -> {
                    println("Unknown status page exception: ${cause.message}")
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}