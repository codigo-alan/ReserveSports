package com.example.templates

import io.ktor.server.html.*
import kotlinx.html.*

class LayoutTemplate: Template<HTML> {

    lateinit var content: String

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
                            href = "/sports/all"
                            +"""Listado de salas"""
                        }
                    }
                    li {
                        a {
                            href = "/sports/add"
                            +"""Nueva reserva"""
                        }
                    }
                    li {
                        a {
                            href = "/sports/aboutus"
                            +"""About us"""
                        }
                    }
                }
            }

        }
    }
}