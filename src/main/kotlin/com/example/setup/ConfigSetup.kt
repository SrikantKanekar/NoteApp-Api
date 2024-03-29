package com.example.setup

import com.example.config.AppConfig
import com.example.config.JWTConfig
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configSetup(testing: Boolean) {
    val appConfig by inject<AppConfig>()

    //testing
    appConfig.testing = testing

    //JWT
    val jwt = environment.config.config("ktor.jwt")
    val secret = if (testing) "123456" else "123456" // System.getenv(JWT_AUTH_SECRET)
    val issuer = jwt.property("issuer").getString()
    val audience = jwt.property("audience").getString()
    val realm = jwt.property("realm").getString()
    appConfig.jwtConfig = JWTConfig(secret, issuer, audience, realm)
}