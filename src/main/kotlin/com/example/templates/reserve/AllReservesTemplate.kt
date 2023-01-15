package com.example.templates.reserve

import com.example.models.reserve.Reserve
import io.ktor.server.html.*
import kotlinx.html.*

class AllReservesTemplate(private val reserves: List<Reserve>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("text-center m-3") {
            div("d-flex mb-2") {
                h4 { +"Todas las reservas" }
            }
            table ("table mx-2"){
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