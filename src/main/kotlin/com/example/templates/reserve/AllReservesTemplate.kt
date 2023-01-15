package com.example.templates.reserve

import com.example.models.reserve.Reserve
import io.ktor.server.html.*
import kotlinx.html.*

class AllReservesTemplate(private val reserves: List<Reserve>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("all") {
            h3 { +"Listado de reservas" }
            table {
                tr {
                    th { +"""Id""" }
                    th { +"""Usuario""" }
                    th { +"""Sala""" }
                    th { +"""Inicio""" }
                    th { +"""Final""" }
                }
                reserves.forEach {
                    tr {
                        td {
                            a {
                                href = "reserves/${it.id}"
                                +"""${it.id}"""
                            }
                        }
                        td { +"""${it.idUser}""" }
                        td { +"""${it.idRoom}""" }
                        td { +"""${it.startDateTime}""" }
                        td { +"""${it.endDateTime}""" }
                    }
                }
            }
        }

    }
}