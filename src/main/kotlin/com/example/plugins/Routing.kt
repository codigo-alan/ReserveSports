package com.example.plugins

import com.example.routes.reserveSportsRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        reserveSportsRouting()
        static("/static") {
            resources("files")
        }

    }

}
