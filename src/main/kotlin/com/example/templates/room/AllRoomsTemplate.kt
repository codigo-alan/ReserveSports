package com.example.templates.room

import com.example.models.room.Room
import io.ktor.server.html.*
import kotlinx.html.*

class AllRoomsTemplate(private val rooms: List<Room>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("text-center m-3") {
            div("d-flex mx-2 mb-2") {
                div {
                    a {
                        href = "rooms/new"
                        button(classes="btn btn-primary") {
                            +"""Crear nueva sala"""
                        }
                    }
                }
            }

            table("table mx-2") {
                tr() {
                    th (){ +"""Imágen""" }
                    th { +"""Nombre""" }
                    th { +"""Descripción""" }
                }
                rooms.forEach {
                    tr (){
                        td() {
                            img (classes = "rounded"){
                                id = "${it.id}"
                                src = "/reserve-sports/uploads/${it.image}"
                                width = "50px"
                                height = "50px"
                            }
                        }
                        td() { +it.name }
                        td (){
                            a {
                                href = "rooms/${it.id}"
                                +"""Ver detalle"""
                            }
                        }
                    }
                }
            }
        }

    }
}