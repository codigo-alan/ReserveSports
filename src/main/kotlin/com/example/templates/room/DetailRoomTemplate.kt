package com.example.templates.room

import com.example.models.reserve.Reserve
import com.example.models.room.Room
import io.ktor.server.html.*
import kotlinx.html.*

class DetailRoomTemplate(private val room: Room, private val reserves: List<Reserve>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("detail") {
            h3 { +"Detalle de la sala" }

            div {
                a {
                    href = "../rooms/delete/${room.id}"
                    button {
                        +"""Eliminar esta sala"""
                    }
                }
                a {
                    href = "../rooms/update/${room.id}"
                    button {
                        +"""Editar"""
                    }
                }

            }

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
                            src = "/static/${room.image}"
                            width = "50px"
                            height = "50px"
                        }
                    }
                    td { +"""${room?.name}""" }
                    td { +"""${room?.description}""" }
                }

            }
            /*h3 { +"${room.name.uppercase()}" }
            div("room") {
                p { +"${room.description}" }
                img {
                    id = "imagenPrueba"
                    src = "/static/${room.image}"
                    width = "50px"
                    height = "50px"
                }
            }*/

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