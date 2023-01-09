package com.example.templates

import io.ktor.server.html.*
import kotlinx.html.*



class LayoutTemplate<T : Template<FlowContent>>(private val template: T): Template<HTML> {
    //val header = Placeholder<FlowContent>()
    val content = TemplatePlaceholder<T>()
    override fun HTML.apply() {
        head {
            link(rel = "stylesheet", href = "/static/main.css", type = "text/css")
            link(rel = "icon", href = "/static/logo.png", type = "image/png")
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

            header {
                div {
                    img {
                        id = "logo"
                        src = "/static/logo.png"
                        width = "100px"
                        height = "100px"
                    }
                }
            }
            nav {
                ul {
                    li {
                        a {
                            href = "/reserve-sports/rooms"
                            +"""Listado de salas"""
                        }
                    }
                    li {
                        a {
                            href = "/reserve-sports/reserves/new"
                            +"""Nueva reserva"""
                        }
                    }
                    /*li {
                        a {
                            href = "/users/detail" //TODO not implemented
                            +"""Info usuario"""
                        }
                    }*/
                    li {
                        a {
                            href = "/reserve-sports/users/new"
                            +"""Nuevo usuario"""
                        }
                    }
                    li {
                        a {
                            href = "/reserve-sports/users"
                            +"""Usuarios"""
                        }
                    }
                }
            }

            insert(template, content)

        }

    }
}
