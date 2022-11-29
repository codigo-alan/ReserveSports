package com.example.routes

import com.example.models.reserve.Reserve
import com.example.models.reserve.ReserveDaoRepository
import com.example.models.room.RoomDaoRepository
import com.example.models.user.UserDaoRepository
import com.example.templates.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.reserveSportsRouting() {

    val roomDaoRepository = RoomDaoRepository()
    val userDaoRepository = UserDaoRepository()
    val reserveDaoRepository = ReserveDaoRepository()

    route("/sports") {

        get("all") {
            val listRooms = roomDaoRepository.getItemList()
            call.respondHtmlTemplate(LayoutTemplate(AllRoomsTemplate(listRooms))) {
            }
        }
        get("detail/{id}") {
            val id = call.parameters["id"]!!
            val room = roomDaoRepository.getItem(id.toInt())
            call.respondHtmlTemplate(LayoutTemplate(DetailRoomTemplate(room!!))) {
            }
        }
        get("add-reserve") {
            call.respondHtmlTemplate(LayoutTemplate(AddReserveTemplate())) {
            }
        }

        //post neccesary to post the data from the input
        post("reserve-action_page") {
            var id: Int = 1
            var startTimeStamp: String = ""
            var endTimeStamp: String = ""
            var idRoom: Int = 1
            var idUser: Int= 1

            val data = call.receiveMultipart()
            data.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "id" -> id = part.value.toInt()
                            "startTimeStamp" -> startTimeStamp = part.value
                            "endTimeStamp" -> endTimeStamp = part.value
                            "idRoom" -> idRoom = part.value.toInt()
                            "idUser" -> idUser = part.value.toInt()
                        }
                    }
                    is PartData.FileItem -> {
                        val fileName = part.originalFileName as String
                        var fileBytes = part.streamProvider().readBytes()
                        File("uploads/$fileName").writeBytes(fileBytes) //create a File in the route that I want
                    }
                    else -> {}
                }

            }

            val reserve = Reserve(id, startTimeStamp, endTimeStamp, idRoom, idUser) //pass all parameters to create the new Movie
            reserveDaoRepository.addItem(reserve)
            //call.respondText("Movie stored correctly and \"$fileName is uploaded to 'uploads/$fileName'\"", status = HttpStatusCode.Created)

        }

    }
    route("/users") {

        get("all") {
            val listUsers = userDaoRepository.getItemList()
            /*call.respondHtmlTemplate(LayoutTemplate(AllUsersTemplate(listUsers))) {
            }*/
        }
        get("detail/{id}") {
            val id = call.parameters["id"]!!
            val user = userDaoRepository.getItem(id.toInt())
            call.respondHtmlTemplate(LayoutTemplate(DetailUserTemplate(user!!))) {
            }
        }

    }

}