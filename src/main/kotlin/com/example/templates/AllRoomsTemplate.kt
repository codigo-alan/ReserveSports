package com.example.templates

import com.example.models.RoomDaoRepository
import io.ktor.server.html.*
import kotlinx.html.*

class AllRoomsTemplate(private val roomDaoRepository: RoomDaoRepository): Template<FlowContent> {
    override fun FlowContent.apply() {
        div("all") {
            h3 { +"Listado de salas" }
            table {
                tr {
                    th { +"""Image""" }
                    th { +"""Name""" }
                    th { +"""Description""" }
                }
                roomDaoRepository.getItemList().forEach {
                    tr {
                        td {
                            img {
                                id = "${it.id}"
                                src = "/static/${it.image}" //TODO need change
                                width = "50px"
                                height = "50px"
                            }
                        }
                        td { +"""${it.name}""" }
                        td {
                            a {
                                href = "detail/${it.id}"
                                +"""Ver detalle"""
                            }
                        }
                    }
                }
            }
        }

    }
}