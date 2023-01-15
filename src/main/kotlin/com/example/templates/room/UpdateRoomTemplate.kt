package com.example.templates.room

import io.ktor.server.html.*
import kotlinx.html.*


class UpdateRoomTemplate(val roomId: Int): Template<FlowContent> { //TODO
    override fun FlowContent.apply() {
        form {
            method = FormMethod.post //this is the method to do
            //action = "/reserve-sports/room-action-page-update/{$roomId}" //route of action
            action = "/reserve-sports/room-action-page-update/${roomId}" //route of action
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
            br {
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
            /*label {
                htmlFor = "image"
                +"""Imágen:"""
            }*/
            /*br {
            }
            input {
                type = InputType.file //type of the input, file because is an image
                id = "image"
                name = "image"
                value = ""
            }*/
            br {
            }
            br {
            }
            input {
                type = InputType.submit
                value = "Guardar cambios"
            }
        }
    }
}