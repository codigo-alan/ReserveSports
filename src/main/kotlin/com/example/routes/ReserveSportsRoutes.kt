package com.example.routes

import com.example.templates.LayoutTemplate
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*

fun Route.reserveSportsRouting() {

    route("/sports") {

        get("all") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "all"
            }
        }

    }

}