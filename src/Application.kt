package com.example

import com.example.data.database.checkPasswordForEmail
import com.example.routes.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        json()
    }

    install(Authentication) {
        basic {
            realm = "Note Server"
            validate { credentials ->
                val email = credentials.name
                val password = credentials.password

                when (checkPasswordForEmail(email, password)) {
                    true -> UserIdPrincipal(email)
                    false -> null
                }
            }
        }
    }

    routing {
        homeRoute()
        registerRoute()
        loginRoute()
        noteRoute()
        deletedNoteRoute()
    }
}