package com.example.routes

import com.example.models.RoomDaoRepository
import com.example.templates.LayoutTemplate
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*

fun Route.reserveSportsRouting() {

    val roomDaoRepository = RoomDaoRepository()

    route("/sports") {

        get("all") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "all"
                this.roomDaoRepository = roomDaoRepository
            }
        }
        get("detail/{id}") {
            val id = call.parameters["id"]!!
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "detail"
                this.roomDaoRepository = roomDaoRepository
                this.roomId = id
            }
        }

    }

}