package com.example.routes

import com.example.models.Formatter
import com.example.models.reserve.Reserve
import com.example.models.reserve.ReserveDaoRepository
import com.example.models.room.RoomDaoRepository
import com.example.models.user.User
import com.example.models.user.UserDaoRepository
import com.example.templates.*
import com.example.templates.reserve.AddReserveTemplate
import com.example.templates.room.AllRoomsTemplate
import com.example.templates.room.DetailRoomTemplate
import com.example.templates.user.AddUserTemplate
import com.example.templates.user.AllUsersTemplate
import com.example.templates.user.DetailUserTemplate
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.time.LocalDateTime

fun Route.reserveSportsRouting() {

    val roomDaoRepository = RoomDaoRepository()
    val userDaoRepository = UserDaoRepository()
    val reserveDaoRepository = ReserveDaoRepository()
    val formatter = Formatter()

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
            //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            var id: Int = -1
            var startTimeStamp: LocalDateTime = LocalDateTime.now()
            var endTimeStamp: LocalDateTime = LocalDateTime.now()
            var idRoom: Int = -1
            var idUser: Int= -1

            val data = call.receiveMultipart()
            data.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "id" -> id = part.value.toInt()
                            "start" -> {
                                startTimeStamp = formatter.formatToDateTime(part.value)
                                /*val textDateTime = part.value.replace('T',' ')
                                startTimeStamp = LocalDateTime.parse(textDateTime, formatter)*/
                            }
                            "end" -> {
                                endTimeStamp = formatter.formatToDateTime(part.value)
                                /*val textDateTime = part.value.replace('T',' ')
                                endTimeStamp = LocalDateTime.parse(textDateTime, formatter)*/
                            }
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

            val reserve = Reserve(id, startTimeStamp.toString(), endTimeStamp.toString(), idRoom, idUser) //pass all parameters to create the new Reserve
            reserveDaoRepository.addItem(reserve)
            call.respondText("Reserva generada", status = HttpStatusCode.Created)

        }

    }
    route("/users") {

        get("all") {
            val listUsers = userDaoRepository.getItemList()
            call.respondHtmlTemplate(LayoutTemplate(AllUsersTemplate(listUsers))) {
            }
        }
        get("detail/{id}") {
            val id = call.parameters["id"]!!
            val user = userDaoRepository.getItem(id.toInt())
            call.respondHtmlTemplate(LayoutTemplate(DetailUserTemplate(user!!))) {
            }
        }
        get("add-user") {
            call.respondHtmlTemplate(LayoutTemplate(AddUserTemplate())) {
            }
        }
        //post neccesary to post the data from the input
        post("user_action_page") {
            var id: Int = 1
            var name: String = ""
            var fileName: String = ""

            val data = call.receiveMultipart()
            data.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "id" -> id = part.value.toInt()
                            "name" -> name = part.value
                        }
                    }
                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        var fileBytes = part.streamProvider().readBytes()
                        File("uploads/$fileName").writeBytes(fileBytes) //create a File in the route that I want
                    }
                    else -> {}
                }

            }

            val user = User(id, name, fileName) //pass all parameters to create the new Reserve
            userDaoRepository.addItem(user)
            call.respondText("Usuario creado", status = HttpStatusCode.Created)

        }
        get("/uploads/{imageName}") {//thius get to see the image
            val imageName = call.parameters["imageName"]
            var file = File("./uploads/$imageName")
            if(file.exists()){
                call.respondFile(File("./uploads/$imageName"))
            }
            else{
                call.respondText("Image not found", status = HttpStatusCode.NotFound)
            }
        }

    }

}