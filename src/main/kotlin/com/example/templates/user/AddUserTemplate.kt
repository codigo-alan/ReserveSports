package com.example.templates.user

import com.example.services.AuthService
import io.ktor.server.html.*
import kotlinx.html.*

class AddUserTemplate(): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("m-3") {
            div("d-flex mb-2") {
                h4 { +"Nuevo usuario" }
            }
            form{
                method = FormMethod.post //this is the method to do
                action = "/reserve-sports/user-action-page" //route of action
                encType= FormEncType.multipartFormData //neccesary to upload images

                label {
                    htmlFor = "name"
                    +"""Nombre:"""
                }

                input (classes = "form-control mb-2"){
                    type = InputType.text
                    id = "name"
                    name = "name"
                    value = ""
                }

                label {
                    htmlFor = "password"
                    +"""Password:"""
                }

                input (classes = "form-control mb-2"){
                    type = InputType.text
                    id = "password"
                    name = "password"
                    value = ""
                }

                label {
                    htmlFor = "role"
                    +"""Role:"""
                }

                select(classes = "form-select mb-2")  {
                    name = "role"
                    id = "role"
                    AuthService.roles.forEach {
                        option {
                            value = "${it}"
                            +"""${it}"""
                        }
                    }

                }

                label {
                    htmlFor = "image"
                    +"""Imágen:"""
                }

                input(classes = "form-control mb-3") {
                    type = InputType.file //type of the input, file because is an image
                    id = "image"
                    name = "image"
                    value = ""
                }

                div("d-flex justify-content-end") {
                    input(classes= "btn btn-primary") {
                        type = InputType.submit
                        value = "Crear"
                    }
                }
            }
        }
    }
}