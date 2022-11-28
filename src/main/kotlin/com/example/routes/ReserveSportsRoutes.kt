package com.example.routes

import com.example.models.RoomDaoRepository
import com.example.templates.AllRoomsTemplate
import com.example.templates.DetailRoomTemplate
import com.example.templates.LayoutTemplate
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*

fun Route.reserveSportsRouting() {

    val roomDaoRepository = RoomDaoRepository()

    route("/sports") {

        get("all") {
            val listRooms = roomDaoRepository.getItemList()
            call.respondHtmlTemplate(LayoutTemplate(AllRoomsTemplate(listRooms))) {
            }
        }
        get("detail/{id}") {
            val id = call.parameters["id"]!!
            val room = roomDaoRepository.getItem(id.toInt())
            call.respondHtmlTemplate(LayoutTemplate(DetailRoomTemplate(room!!))) {

            }
        }

    }

}