package com.example.templates.user

import io.ktor.server.html.*
import kotlinx.html.*

class AddUserTemplate(): Template<FlowContent> {
    override fun FlowContent.apply() {
        form {
            method = FormMethod.post //this is the method to do
            action = "/users/user_action_page" //route of action
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
            }
            br {
            }*/
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
                value = "Submit"
            }
        }
    }
}