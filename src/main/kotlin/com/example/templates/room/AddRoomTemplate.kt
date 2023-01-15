package com.example.templates.room

import io.ktor.server.html.*
import kotlinx.html.*

class AddRoomTemplate(): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("m-3") {
            div("d-flex mb-2") {
                h4 { +"Nueva sala" }
            }
            form(classes = "form-control") {
                method = FormMethod.post //this is the method to do
                action = "/reserve-sports/room-action-page" //route of action
                encType= FormEncType.multipartFormData //neccesary to upload images

                label {
                    htmlFor = "name"
                    +"""Nombre"""
                }

                input(classes = "form-control") {
                    type = InputType.text
                    id = "name"
                    name = "name"
                    value = ""
                }
                br {
                }
                label {
                    htmlFor = "description"
                    +"""Descripción"""
                }
                input (classes = "form-control"){
                    type = InputType.text
                    id = "description"
                    name = "description"
                    value = ""
                }
                br {
                }

                label {
                    htmlFor = "image"
                    +"""Imágen"""
                }
                br {
                }
                input(classes = "form-control") {
                    type = InputType.file //type of the input, file because is an image
                    id = "image"
                    name = "image"
                    value = ""
                }
                br {
                }
                div("d-flex justify-content-end") {
                    input(classes = "btn btn-primary")  {
                        type = InputType.submit
                        value = "Crear"
                    }
                }
            }
        }
    }
}