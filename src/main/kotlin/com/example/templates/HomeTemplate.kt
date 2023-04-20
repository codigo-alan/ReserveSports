package com.example.templates

import com.example.models.UserSession
import com.example.services.AuthService
import io.ktor.server.html.*
import kotlinx.html.*

class HomeTemplate(private val userSession: UserSession) : Template<FlowContent> {
    override fun FlowContent.apply() {
        div("m-3") {
            div("d-flex justify-content-between mb-2") {
                h4 { +"${userSession.name}" }
            }
            div("d-flex justify-content-between mb-2") {
                h4 { +"${AuthService.user}" }
            }
            p { +"""Has ingresado: ${userSession.count} veces""" }
            div {
                a {
                    href = "/logout"//TODO verify route and the log out
                    button(classes="btn btn-secondary") {
                        +"""Log out"""
                    }
                }
            }
        }
    }
}