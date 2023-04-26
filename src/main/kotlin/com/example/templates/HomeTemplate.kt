package com.example.templates

import com.example.models.UserSession
import com.example.services.AuthService
import io.ktor.server.html.*
import kotlinx.html.*

class HomeTemplate(private val userSession: UserSession) : Template<FlowContent> {
    override fun FlowContent.apply() {
        div("m-3") {
            div("d-flex justify-content-between mb-2") {
                h4 { +"${userSession.name.uppercase()}" }
            }
            div("d-flex flex-column justify-content-between mb-2") {
                h5 { +"""Id: ${AuthService.user.id}""" }
                h5 { +"""Rol: ${AuthService.user.role}""" }
            }
            p { +"""Has ingresado: ${userSession.count} veces""" }
            div {
                a {
                    href = "/logout"
                    button(classes="btn btn-secondary") {
                        +"""Log out"""
                    }
                }
            }
        }
    }
}