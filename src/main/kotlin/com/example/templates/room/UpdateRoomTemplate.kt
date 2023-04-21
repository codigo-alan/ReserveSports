package com.example.templates.room

import com.example.models.room.Room
import io.ktor.server.html.*
import kotlinx.html.*


class UpdateRoomTemplate(val room: Room): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("m-3") {
            div("d-flex mb-2") {
                h4 { +"Editar sala: ${room.id}" }
            }
            form(classes = "form-control w-50 m-auto") {
                method = FormMethod.post //this is the method to do
                action = "/reserve-sports/room-action-page-update/${room.id}" //route of action
                encType= FormEncType.multipartFormData //neccesary to upload images

                label(classes = "form-label") {
                    htmlFor = "name"
                    +"""Nombre"""
                }
                br {
                }
                input(classes = "form-control") {
                    type = InputType.text
                    id = "name"
                    name = "name"
                    value = "${room.name}"
                }
                br {
                }
                label (classes = "form-label"){
                    htmlFor = "description"
                    +"""Descripción"""
                }
                br {
                }
                input (classes = "form-control"){
                    type = InputType.text
                    id = "description"
                    name = "description"
                    value = "${room.description}"
                }
                br {
                }
                label (classes = "form-label"){
                    htmlFor = "image"
                    +"""Imágen"""
                }
                br {
                }
                input(classes = "form-control") {
                    type = InputType.file //type of the input, file because is an image
                    id = "image"
                    name = "image"
                    value = "${room.image}"
                }
                br {
                }
                br {
                }
                div("d-flex justify-content-end") {
                    input(classes = "btn btn-primary") {
                        type = InputType.submit
                        value = "Actualizar"
                    }
                }
            }
        }
    }
}