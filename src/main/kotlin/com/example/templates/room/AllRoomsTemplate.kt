package com.example.templates.room

import com.example.models.room.Room
import io.ktor.server.html.*
import kotlinx.html.*

class AllRoomsTemplate(private val rooms: List<Room>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("all") {
            h3 { +"Listado de salas" }
            div {
                a {
                    href = "rooms/new"
                    button {
                        +"""Crear nueva sala"""
                    }
                }

            }
            table {
                tr {
                    th { +"""Image""" }
                    th { +"""Name""" }
                    th { +"""Description""" }
                }
                rooms.forEach {
                    tr {
                        td {
                            img (classes = "tableImg"){
                                id = "${it.id}"
                                src = "/static/${it.image}"
                                width = "50px"
                                height = "50px"
                            }
                        }
                        td { +"""${it.name}""" }
                        td {
                            a {
                                href = "rooms/${it.id}"
                                +"""Ver detalle"""
                            }
                        }
                    }
                }
            }
        }

    }
}