package com.example.templates

import io.ktor.server.html.*
import kotlinx.html.*

class LoginTemplate: Template<HTML> {

    override fun HTML.apply() {
        head {
            link {
                href = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
                rel = "stylesheet"
                attributes["integrity"] = "sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
                attributes["crossorigin"] = "anonymous"
            }
        }
        body {

            script {
                src = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
                integrity = "sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
                attributes["crossorigin"] = "anonymous"
            }

            div("m-3") {
                div("d-flex mb-2") {
                    h4 { +"Sign in" }
                }
                form{

                    action = "/login"
                    method = FormMethod.post

                    label {
                        htmlFor = "username"
                        +"""Nombre:"""
                    }

                    input (classes = "form-control mb-2"){
                        type = InputType.text
                        id = "username"
                        name = "username"
                        value = ""
                    }
                    label {
                        htmlFor = "password"
                        +"""Contraseña:"""
                    }

                    input (classes = "form-control mb-2"){
                        type = InputType.password
                        id = "password"
                        name = "password"
                        value = ""
                    }
                    div("d-flex justify-content-end") {
                        input(classes= "btn btn-primary") {
                            type = InputType.submit
                            value = "Login"
                        }
                    }
                }
            }
        }

    }

}