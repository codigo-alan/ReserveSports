package com.example.templates

import com.example.models.user.User
import io.ktor.server.html.*
import kotlinx.html.*

class DetailUserTemplate(private val user: User): Template<FlowContent> {

    override fun FlowContent.apply() {

        div("detail") {
            p { +"""ID de usuario: ${user?.id}""" }
            p { +"""Nombre de usuario: ${user?.name}""" }
        }

    }

}