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
            p { +"""Usuario asignado: ${reserve?.idRoom}""" } //TODO obtain these data in a join, to see the name, not id
            p { +"""Sala reservada: ${reserve?.idUser}""" } //TODO obtain these data in a join, to see the name, not id
            p { +"""Sala reservada: $roomName""" }
            p { +"""Usuario asignado: $userName""" }

        }

        div {
            a {
                href = "../delete/${reserve?.id}"
                button {
                    +"""Borrar reserva"""
                }
            }

        }

    }

}