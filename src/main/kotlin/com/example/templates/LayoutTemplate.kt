package com.example.templates

import com.example.models.RoomDaoRepository
import io.ktor.server.html.*
import kotlinx.html.*

class LayoutTemplate: Template<HTML> {

    lateinit var content: String
    lateinit var roomDaoRepository: RoomDaoRepository
    lateinit var roomId: String

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

            when (content) {
                "all" -> {
                    insert(AllRoomsTemplate(roomDaoRepository), TemplatePlaceholder())
                }
                "detail" -> {
                    insert(DetailRoomTemplate(roomDaoRepository, roomId), TemplatePlaceholder())
                }

            }

        }
    }
}