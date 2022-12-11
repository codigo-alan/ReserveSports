package com.example.templates.reserve

import com.example.models.reserve.Reserve
import io.ktor.server.html.*
import kotlinx.html.*

class DetailReserveTemplate(private val reserve: Reserve):
    Template<FlowContent> {

    override fun FlowContent.apply() {

        div("detail") {
            p { +"""Nro de reserva: ${reserve?.id}""" }
            p { +"""Hora Inicio: ${reserve?.startDateTime}""" }
            p { +"""Hora Fin: ${reserve?.endDateTime}""" }
            p { +"""Usuario asignado: ${reserve?.idRoom}""" } //TODO obtain these data in a join, to see the name, not id
            p { +"""Sala reservada: ${reserve?.idUser}""" } //TODO obtain these data in a join, to see the name, not id
        }

        div {
            button {
                onClick = "../delete/${reserve?.id}" //TODO create function to go to route to delete
                a {
                    href = "../delete/${reserve?.id}"//TODO not pass properly the url
                    +"""CLICK to delete"""
                }
                +"""Borrar reserva"""
            }
        }

    }

}