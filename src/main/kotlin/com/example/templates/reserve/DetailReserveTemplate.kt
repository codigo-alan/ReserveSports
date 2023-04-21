package com.example.templates.reserve

import com.example.models.reserve.Reserve
import io.ktor.server.html.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.Query

class DetailReserveTemplate(private val reserve: Reserve, private val userName: String, private val roomName: String):
    Template<FlowContent> {

    override fun FlowContent.apply() {

        div("m-3") {
            div("d-flex justify-content-between mb-2") {
                h4 { +"Reserva nro: ${reserve?.id}" }
                div {
                    a {
                        href = "../reserves/delete/${reserve?.id}"
                        button(classes = "btn btn-warning"){
                            +"""Borrar"""
                        }
                    }

                }
            }
            form(classes = "w-50 m-auto"){

                label (classes = "form-label") {
                    htmlFor = "start"
                    +"""Inicio:"""
                }

                input (classes = "form-control") {
                    type = InputType.dateTimeLocal
                    id = "start"
                    name = "start"
                    value = "${reserve?.startDateTime}"
                    readonly = true
                }
                br {
                }
                label (classes = "form-label") {
                    htmlFor = "end"
                    +"""Final:"""
                }

                input(classes = "form-control")  {
                    type = InputType.dateTimeLocal
                    id = "end"
                    name = "end"
                    value = "${reserve?.endDateTime}"
                    readonly = true
                }
                br {
                }

                label (classes = "form-label") {
                    htmlFor = "idUser"
                    +"""Usuario:"""
                }
                input(classes = "form-control")  {
                    name = "idUser"
                    id = "idUser"
                    value = "${userName}"
                    readonly = true
                }

                br {
                }

                label (classes = "form-label") {
                    htmlFor = "idRoom"
                    +"""Sala:"""
                }
                input (classes = "form-control") {
                    name = "idRoom"
                    id = "idRoom"
                    value = "${roomName}"
                    readonly = true
                }

            }
        }

    }

}