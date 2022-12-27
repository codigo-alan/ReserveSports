package com.example.templates.reserve

import com.example.models.room.Room
import com.example.models.user.User
import io.ktor.server.html.*
import kotlinx.html.*

class AddReserveTemplate(private val listUsers: List<User>, private val listRooms: List<Room>): Template<FlowContent> {
    override fun FlowContent.apply() {
        form {
            method = FormMethod.post //this is the method to do
            action = "/sports/reserve-action_page" //route of action
            encType= FormEncType.multipartFormData //neccesary to upload images
            /*label {
                htmlFor = "id"
                +"""Id:"""
            }
            br {
            }
            input {
                type = InputType.number
                id = "id"
                name = "id"
                value = ""
            }
            br {
            }*/
            label {
                htmlFor = "start"
                +"""Inicio:"""
            }
            br {
            }
            input {
                type = InputType.dateTimeLocal
                id = "start"
                name = "start"
                value = ""
            }
            br {
            }
            br {
            }
            label {
                htmlFor = "end"
                +"""Final:"""
            }
            br {
            }
            input {
                type = InputType.dateTimeLocal
                id = "end"
                name = "end"
                value = ""
            }
            br {
            }
            br {
            }
            /*label {
                htmlFor = "idRoom"
                +"""Id Room:"""
            }
            br {
            }
            input {
                type = InputType.number
                id = "idRoom"
                name = "idRoom"
                value = ""
            }
            br {
            }
            br {
            }
            label {
                htmlFor = "idUser"
                +"""Id User:"""
            }
            br {
            }
            input {
                type = InputType.number
                id = "idUser"
                name = "idUser"
                value = ""
            }

            br {
            }
            br {
            }*/

            label {
                htmlFor = "idUser"
                +"""Usuario:"""
            }
            select {
                name = "idUser"
                id = "idUser"
                listUsers.forEach {
                    option {
                        value = "${it.id}"
                        +"""${it.name}"""
                    }
                }

            }

            br {
            }
            br {
            }

            label {
                htmlFor = "idRoom"
                +"""Sala:"""
            }
            select {
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
            br {
            }

            input {
                type = InputType.submit
                value = "Submit"
            }
        }

    }
}