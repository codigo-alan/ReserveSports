package com.example.templates.user

import com.example.models.user.User
import io.ktor.server.html.*
import kotlinx.html.*

class AllUsersTemplate(private val users: List<User>): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("all") {
            h3 { +"Listado de usuarios" }
            table {
                tr {
                    th { +"""Image""" }
                    th { +"""Name""" }
                }
                users.forEach {
                    tr {
                        td {
                            img (classes = "tableImg"){
                                id = "${it.id}"
                                src = "/reserve-sports/uploads/${it.profileImg}" //this is the source of the image, view GET in movie routes
                                width = "50px"
                                height = "50px"
                            }
                        }
                        td { +"""${it.name}""" }
                        td {
                            a {
                                href = "users/${it.id}"
                                +"""Ver usuario"""
                            }
                        }
                    }
                }
            }
        }

    }
}