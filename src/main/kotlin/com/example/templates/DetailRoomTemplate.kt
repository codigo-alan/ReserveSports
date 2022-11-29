package com.example.templates

import com.example.models.room.Room
import io.ktor.server.html.*
import kotlinx.html.*

class DetailRoomTemplate(private val room: Room): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("detail") {
            h3 { +"Detalle de la sala" }
            table {
                style = "width:100%"
                tr {
                    th { +"""Imágen""" }
                    th { +"""Nombre""" }
                    th { +"""Descripción""" }
                }

                tr {
                    td {
                        img {
                            id = "imagenPrueba"
                            src = "/static/files/logo.png"
                            width = "50px"
                            height = "50px"
                        }
                    }
                    td { +"""${room?.name}""" }
                    td { +"""${room?.description}""" }
                }

            }
        }

    }
}