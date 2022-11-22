package com.example.templates

import com.example.models.RoomDaoRepository
import io.ktor.server.html.*
import kotlinx.html.*

class DetailRoomTemplate(private val roomDaoRepository: RoomDaoRepository, private val roomId: String): Template<FlowContent> {
    override fun FlowContent.apply() {
        val room = roomDaoRepository.getItem(roomId.toInt())
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