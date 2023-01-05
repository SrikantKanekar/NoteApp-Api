package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.homeRoute() {
    route("/") {
        get {
            call.respondText("Hi, this is Ktor note server!")
        }
    }
}