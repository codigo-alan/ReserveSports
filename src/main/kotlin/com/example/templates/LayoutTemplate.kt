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
        }

        body {
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
                            href = "/reserve-sports/reserve/new"
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
