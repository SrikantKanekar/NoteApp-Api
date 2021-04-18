package com.example.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.homeRoute() {
    route("/") {
        get {
            call.respondText("Hi, this is Ktor note server")
        }
    }
}