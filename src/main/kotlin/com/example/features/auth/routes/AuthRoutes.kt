package com.example.features.auth.routes

import com.example.config.AppConfig
import com.example.features.auth.data.AuthRepository
import com.example.util.USER_AUTH
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.registerAuthRoutes() {

    val authRepository by inject<AuthRepository>()
    val appConfig by inject<AppConfig>()

    routing {
        route("/auth") {
            loginRoute(authRepository, appConfig.jwtConfig)
            registerRoute(authRepository, appConfig.jwtConfig)

            authenticate(USER_AUTH) {
                resetPassword(authRepository)
            }
        }
    }
}