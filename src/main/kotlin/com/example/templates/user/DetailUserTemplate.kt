package com.example.templates.user

import com.example.models.reserve.Reserve
import com.example.models.user.User
import io.ktor.server.html.*
import kotlinx.html.*

class DetailUserTemplate(private val user: User, private val reserves: List<Reserve>, private val reservesActives: List<Reserve>): Template<FlowContent> {

    override fun FlowContent.apply() {

        div("m-3") {
            div("d-flex justify-content-between mb-2") {
                h4 { +"Usuario nro: ${user?.id}" }
                div {
                    a {
                        href = "../users/delete/${user.id}"
                        button(classes = "btn btn-warning"){
                            +"""Borrar"""
                        }
                    }

                }
            }
            p { +"""Nombre de usuario: ${user?.name}""" }

        }
        div("text-center m-3") {
            div("d-flex justify-content-between mb-2") {
                h4 { +"Reservas en curso" }
            }

            table ("table m-2"){
                tr {
                    th { +"""Nro de reserva""" }
                    th { +"""Hora de inicio""" }
                    th { +"""Hora de fin""" }
                }
                reservesActives.forEach {
                    tr {
                        td {
                            a {
                                href = "../../reserve-sports/reserves/${it.id}"
                                +"""${it?.id}"""
                            }
                        }
                        td { +"""${it?.startDateTime}""" }
                        td { +"""${it?.endDateTime}""" }
                    }
                }
            }
        }
        div("text-center m-3") {
            div("d-flex justify-content-between mb-2") {
                h4 { +"Historial de reservas" }
            }

            table ("table m-2"){
                tr {
                    th { +"""Nro de reserva""" }
                    th { +"""Hora de inicio""" }
                    th { +"""Hora de fin""" }
                }
                reserves.forEach {
                    tr {
                        td {
                            a {
                                href = "../../reserve-sports/reserves/${it.id}"
                                +"""${it?.id}"""
                            }
                        }
                        td { +"""${it?.startDateTime}""" }
                        td { +"""${it?.endDateTime}""" }
                    }
                }
            }
        }
    }

}