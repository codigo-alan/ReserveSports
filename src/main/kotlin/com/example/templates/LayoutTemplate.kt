package com.example.templates

import com.example.models.RoomDaoRepository
import io.ktor.server.html.*
import kotlinx.html.*



class LayoutTemplate<T : Template<FlowContent>>(private val template: T): Template<HTML> {
    val header = Placeholder<FlowContent>()
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

            insert(template, content)

        }

    }
}



class ContentTemplate1: Template<FlowContent> {
    val articleTitle = Placeholder<FlowContent>()
    val articleText = Placeholder<FlowContent>()
    override fun FlowContent.apply() {
        article {
            h2 {
                insert(articleTitle)
            }
            p {
                insert(articleText)
            }
        }
    }
}

class ContentTemplate2: Template<FlowContent> {
    override fun FlowContent.apply() {
        article {
            h2 {
                + "Just Hello!"
            }
        }
    }
}


/*
class LayoutTemplate : Template<HTML> {

    lateinit var content: String
    lateinit var roomDaoRepository: RoomDaoRepository
    lateinit var roomId: String

    lateinit var template: T

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

            insert(template, TemplatePlaceholder())
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
*/
