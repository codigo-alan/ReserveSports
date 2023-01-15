package com.example.templates.room

import io.ktor.server.html.*
import kotlinx.html.*

class AddRoomTemplate(): Template<FlowContent> {
    override fun FlowContent.apply() {
        form {
            method = FormMethod.post //this is the method to do
            action = "/reserve-sports/room-action-page" //route of action
            encType= FormEncType.multipartFormData //neccesary to upload images

            label {
                htmlFor = "name"
                +"""Nombre:"""
            }
            br {
            }
            input {
                type = InputType.text
                id = "name"
                name = "name"
                value = ""
            }
            label {
                htmlFor = "description"
                +"""Descripción:"""
            }
            br {
            }
            input {
                type = InputType.text
                id = "description"
                name = "description"
                value = ""
            }
            br {
            }
            br {
            }
            label {
                htmlFor = "image"
                +"""Imágen:"""
            }
            br {
            }
            input {
                type = InputType.file //type of the input, file because is an image
                id = "image"
                name = "image"
                value = ""
            }
            br {
            }
            br {
            }
            input {
                type = InputType.submit
                value = "Crear"
            }
        }
    }
}