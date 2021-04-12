package com.example

import com.example.data.database.checkPasswordForEmail
import com.example.routes.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    val port = System.getenv("PORT")?.toInt() ?: 23567
    embeddedServer(Netty, port){

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
            testRoute()
            registerRoute()
            loginRoute()
            noteRoute()
            deletedNoteRoute()
        }
    }.start(wait = true)
}

