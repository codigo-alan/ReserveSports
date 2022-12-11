package com.example.templates.room

import com.example.models.reserve.Reserve
import com.example.models.room.Room
import io.ktor.server.html.*
import kotlinx.html.*

class DetailRoomTemplate(private val room: Room, private val reserves: List<Reserve>): Template<FlowContent> {
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
        div("detail") {
            h3 { +"Reservas en la sala" }
            table {
                style = "width:100%"
                tr {
                    th { +"""Hora de inicio""" }
                    th { +"""Hora de fin""" }
                }
                reserves.forEach {
                    tr {
                        td { +"""${it?.startDateTime}""" }
                        td { +"""${it?.endDateTime}""" }
                    }
                }


            }

        }

    }
}