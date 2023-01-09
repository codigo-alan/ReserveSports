package com.example.templates.user

import com.example.models.reserve.Reserve
import com.example.models.user.User
import io.ktor.server.html.*
import kotlinx.html.*

class DetailUserTemplate(private val user: User, private val reserves: List<Reserve>, private val reservesActives: List<Reserve>): Template<FlowContent> {

    override fun FlowContent.apply() {

        div("detail") {
            p { +"""ID de usuario: ${user?.id}""" }
            p { +"""Nombre de usuario: ${user?.name}""" }
            div {
                a {
                    href = "../users/delete/${user.id}"
                    button {
                        +"""Dar de baja este usuario"""
                    }
                }

            }
        }
        div("detail") {
            h3 { +"Reservas en curso" }
            table {
                style = "width:100%"
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
        div("detail") {
            h3 { +"Historial de reservas" }
            table {
                style = "width:100%"
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