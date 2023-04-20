package com.example.templates.room

import com.example.models.Role
import com.example.models.reserve.Reserve
import com.example.models.room.Room
import com.example.services.AuthService
import io.ktor.server.html.*
import kotlinx.html.*

class DetailRoomTemplate(private val room: Room, private val reserves: List<Reserve>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("text-center m-3") {
            div("d-flex justify-content-between") {
                h4 { +"${room.name.uppercase()}" }

                if (AuthService.user.role == Role.ADMIN) {
                    div {
                        a {
                            href = "../rooms/update/${room.id}"
                            button(classes = "btn btn-secondary me-2") {
                                +"""Editar"""
                            }
                        }
                        a {
                            href = "../rooms/delete/${room.id}"
                            button(classes = "btn btn-warning") {
                                +"""Eliminar esta sala"""
                            }
                        }

                    }
                }
            }

            table("table mx-2") {
                tr {
                    th { +"""Imágen""" }
                    th { +"""Nombre""" }
                    th { +"""Descripción""" }
                }

                tr {
                    td {
                        img (classes = "rounded"){
                            id = "imagenPrueba"
                            src = "/reserve-sports/uploads/${room.image}"
                            width = "50px"
                            height = "50px"
                        }
                    }
                    td { +"""${room?.name}""" }
                    td { +"""${room?.description}""" }
                }

            }
        }
        div("text-center mx-3 mb-3") {
            div("d-flex justify-content-start"){
                h4 { +"Reservas en la sala" }
            }
            table ("table mx-2"){
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