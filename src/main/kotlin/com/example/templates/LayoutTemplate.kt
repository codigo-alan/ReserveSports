package com.example.templates

import io.ktor.server.html.*
import kotlinx.html.*


class LayoutTemplate<T : Template<FlowContent>>(private val template: T): Template<HTML> {
    //val header = Placeholder<FlowContent>()
    val content = TemplatePlaceholder<T>()
    override fun HTML.apply() {
        head {
            link(rel = "stylesheet", href = "/static/main.css", type = "text/css")
            link(rel = "icon", href = "/static/LogoHD.png", type = "image/png")
        }

        body {
            header {
                div("area") {
                    img {
                        id = "logo"
                        src = "/static/LogoHD.png"
                        width = "100px"
                        height = "100px"
                       // alt = "LogoHD"


                    }
                    ul("circles") {
                        li {  }
                        li {  }
                        li {  }
                        li {  }
                        li {  }
                        li {  }
                        li {  }
                        li {  }
                        li {  }
                        li {  }
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
                            href = "/sports/add-reserve"
                            +"""Nueva reserva"""
                        }
                    }
                    li {
                        a {
                            href = "/users/detail" //TODO not implemented
                            +"""Info usuario"""
                        }
                    }
                }
            }

            insert(template, content)
            /**Firma y fecha de la página, ¡sólo por cortesía!
            <address>Creada el 5 de abril de 2004<br>
            por mí mismo.</address> */


        }
     
    }
}
