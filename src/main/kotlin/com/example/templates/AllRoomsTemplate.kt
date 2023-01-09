package com.example.templates

import com.example.models.room.Room
import io.ktor.server.html.*
import kotlinx.html.*

class AllRoomsTemplate(private val rooms: List<Room>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("all") {
            h3 { +"Listado de salas" }
            table {
                tr {
                    th { +"""Image"""  }
                    th { +"""Name""" }
                    th { +"""Description""" }
                }
                rooms.forEach {
                    tr {
                        td {
                            img {
                                id = "${it.id}"
                                src = "/static/${it.image}" //TODO need change
                                width = "50px"
                                height = "50px"
                            }
                        }
                        td { +it.name }
                        td {
                            a {
                                href = "detail/${it.id}"
                                +"""Ver detalle"""
                            }
                        }
                    }
                }
            }
        }
        /**
         * este div de abajo sirve para añadir cajetas donde va info. como la caja que de listado de sala
         */
//        div("casa") {
//            div { +"adios" }
//            div { +"ghh" }
//            div { +"ff" }
//            div { +"ff" }
//            div { +"ffbbhj" } }

    }
}