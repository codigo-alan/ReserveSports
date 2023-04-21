package com.example.templates.reserve

import com.example.models.Role
import com.example.models.room.Room
import com.example.models.user.User
import com.example.services.AuthService
import io.ktor.server.html.*
import kotlinx.html.*

class AddReserveTemplate(private val listUsers: List<User>, private val listRooms: List<Room>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("m-3") {
            div("d-flex mb-2") {
                h4 { +"Nueva reserva" }
            }
            form(classes = "form-control w-50 m-auto")  {
                method = FormMethod.post //this is the method to do
                action = "/reserve-sports/reserve-action-page" //route of action
                encType= FormEncType.multipartFormData //neccesary to upload images
                label (classes = "form-label") {
                    htmlFor = "start"
                    +"""Inicio:"""
                }
                br {
                }
                input (classes = "form-control") {
                    type = InputType.dateTimeLocal
                    id = "start"
                    name = "start"
                    value = ""
                }
                br {
                }
                label (classes = "form-label") {
                    htmlFor = "end"
                    +"""Final:"""
                }
                br {
                }
                input(classes = "form-control")  {
                    type = InputType.dateTimeLocal
                    id = "end"
                    name = "end"
                    value = ""
                }
                br {
                }

                label (classes = "form-label") {
                    htmlFor = "idUser"
                    +"""Usuario: ${AuthService.user.name}"""
                }
                if (AuthService.user.role == Role.ADMIN) {
                    select(classes = "form-select")  {
                        name = "idUser"
                        id = "idUser"
                        listUsers.forEach {
                            option {
                                value = "${it.id}"
                                +"""${it.name}"""
                            }
                        }
                    }
                } else {
                    input (classes = "form-control mb-2"){
                        type = InputType.text
                        id = "idUser"
                        name = "idUser"
                        readonly = true
                        value = "${AuthService.user.id}"
                    }
                }


                br {
                }

                label (classes = "form-label") {
                    htmlFor = "idRoom"
                    +"""Sala:"""
                }
                select (classes = "form-select") {
                    name = "idRoom"
                    id = "idRoom"
                    listRooms.forEach {
                        option {
                            value = "${it.id}"
                            +"""${it.name}"""
                        }
                    }

                }

                br {
                }

                div("d-flex justify-content-end") {
                    input(classes= "btn btn-primary") {
                        type = InputType.submit
                        value = "Reservar"
                    }
                }
            }
        }

    }
}