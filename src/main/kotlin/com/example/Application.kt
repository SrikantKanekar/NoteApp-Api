package com.example

import com.example.di.productionModules
import com.example.setup.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import org.koin.core.module.Module

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.module(
    testing: Boolean = false,
    koinModules: List<Module> = productionModules
) {
    koinSetup(koinModules)
    configSetup(testing)
    contentNegotiationSetup()
    corsSetup()
    authSetup()
    requestValidationSetup()
    routesSetup()
}