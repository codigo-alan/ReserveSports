package com.example.templates.reserve

import com.example.models.reserve.Reserve
import io.ktor.server.html.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.Query

class DetailReserveTemplate(private val reserve: Reserve, private val userName: String, private val roomName: String):
    Template<FlowContent> {

    override fun FlowContent.apply() {

        div("detail") {
            p { +"""Nro de reserva: ${reserve?.id}""" }
            p { +"""Hora Inicio: ${reserve?.startDateTime}""" }
            p { +"""Hora Fin: ${reserve?.endDateTime}""" }
            p { +"""Sala reservada: $roomName""" }
            p { +"""Usuario asignado: $userName""" }

        }

        div {
            a {
                href = "../reserves/delete/${reserve?.id}"
                button {
                    +"""Borrar reserva"""
                }
            }

        }

    }

}