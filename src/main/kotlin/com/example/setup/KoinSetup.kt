package com.example.setup

import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.ktor.plugin.Koin
import org.koin.logger.SLF4JLogger

fun Application.koinSetup(koinModules: List<Module>) {
    install(Koin) {
        SLF4JLogger()
        modules(koinModules)
    }
}