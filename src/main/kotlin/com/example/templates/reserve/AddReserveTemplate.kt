package com.example.templates.reserve

import io.ktor.server.html.*
import kotlinx.html.*

class AddReserveTemplate(): Template<FlowContent> {
    override fun FlowContent.apply() {
        form {
            method = FormMethod.post //this is the method to do
            action = "/sports/reserve-action_page" //route of action
            encType= FormEncType.multipartFormData //neccesary to upload images
            label {
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
            }
            label {
                htmlFor = "start"
                +"""Inicio:"""
            }
            br {
            }
            input {
                type = InputType.text
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
                type = InputType.text
                id = "end"
                name = "end"
                value = ""
            }
            br {
            }
            br {
            }
            label {
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
            }
            input {
                type = InputType.submit
                value = "Submit"
            }
        }

    }
}