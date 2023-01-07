package com.example

import com.example.data.database.checkPasswordForEmail
import com.example.routes.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
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
    }
}