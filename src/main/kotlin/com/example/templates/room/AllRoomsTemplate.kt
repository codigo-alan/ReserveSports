package com.example.templates.room

import com.example.models.room.Room
import io.ktor.server.html.*
import kotlinx.html.*

class AllRoomsTemplate(private val rooms: List<Room>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("all") {
            h3 { +"Listado de salas" }
            table {
                tr {
                    th { +"""Image""" }
                    th { +"""Name""" }
                    th { +"""Description""" }
                }
                rooms.forEach {
                    tr {
                        td {//TODO not completed
                            img (classes = "tableImg"){
                                id = "${it.id}"
                                src = "/sports/uploads/${it.image}" //this is the source of the image, view GET in movie routes
                                width = "50px"
                                height = "50px"
                            }
                        }
                        td { +"""${it.name}""" }
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

    }
}