package com.example.routes

import com.example.models.Action
import com.example.models.FileRepo
import com.example.models.Formatter
import com.example.models.reserve.ReserveDaoRepository
import com.example.models.reserve.ReserveInsertData
import com.example.models.room.RoomDaoRepository
import com.example.models.room.RoomInsertData
import com.example.models.user.UserDaoRepository
import com.example.models.user.UserInsertData
import com.example.templates.*
import com.example.templates.reserve.AddReserveTemplate
import com.example.templates.reserve.AllReservesTemplate
import com.example.templates.reserve.DetailReserveTemplate
import com.example.templates.room.AddRoomTemplate
import com.example.templates.room.AllRoomsTemplate
import com.example.templates.room.DetailRoomTemplate
import com.example.templates.room.UpdateRoomTemplate
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
    val fileRepo = FileRepo()
    val formatter = Formatter()
    route("/"){
        get() {
            call.respondRedirect("reserve-sports/rooms")
        }
    }

    route("/reserve-sports") {
         get("actions") {
             val listActions = fileRepo.listActions
             call.respondText(text = listActions.toString())
         }

        get("rooms") {
            val listRooms = roomDaoRepository.getItemList()
            call.respondHtmlTemplate(LayoutTemplate(AllRoomsTemplate(listRooms))) {
            }
        }
        get("rooms/{id}") {
            val id = call.parameters["id"]!!
            val room = roomDaoRepository.getItem(id.toInt())
            val reserves = reserveDaoRepository.getItemListByRoom(id.toInt())
            call.respondHtmlTemplate(LayoutTemplate(DetailRoomTemplate(room!!, reserves))) {
            }
        }

        get("rooms/delete/{id}") {
            val id = call.parameters["id"]!!
            roomDaoRepository.deleteItem(id.toInt())
            call.respondRedirect("../../rooms")
        }

        get("rooms/new") {
            call.respondHtmlTemplate(LayoutTemplate(AddRoomTemplate())) {
            }
        }

        get("rooms/update/{id}") {
            val id = call.parameters["id"]!!
            val room = roomDaoRepository.getItem(id.toInt())
            call.respondHtmlTemplate(LayoutTemplate(UpdateRoomTemplate(room!!))) {
            }
        }
        post("room-action-page-update/{id}") {
            val id = call.parameters["id"]!!
            var name: String = ""
            var description: String = ""
            var fileName: String = ""

            val data = call.receiveMultipart()
            data.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "name" -> name = part.value
                            "description" -> description = part.value
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

            val room = RoomInsertData(name, description, fileName) //pass all parameters to create the new User
            roomDaoRepository.updateItem(id.toInt(), room)
            call.respondRedirect("../rooms")
        }

        post("room-action-page") {
            var name: String = ""
            var description: String = ""
            var fileName: String = ""

            val data = call.receiveMultipart()
            data.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "name" -> name = part.value
                            "description" -> description = part.value
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

            val room = RoomInsertData(name, description, fileName) //pass all parameters to create the new Room
            roomDaoRepository.addItem(room)
            call.respondRedirect("rooms")
        }

        get("reserves") {
            val listReserves = reserveDaoRepository.getItemList()
            call.respondHtmlTemplate(LayoutTemplate(AllReservesTemplate(listReserves))) {
            }
        }


        get("reserves/new") {
            val listUsers = userDaoRepository.getItemList()
            val listRooms = roomDaoRepository.getItemList()
            call.respondHtmlTemplate(LayoutTemplate(AddReserveTemplate(listUsers,listRooms))) {
            }
        }

        get("reserves/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val reserve = reserveDaoRepository.getItem(id)
            val userName = reserveDaoRepository.getUserName(id)
            val roomName = reserveDaoRepository.getRoomName(id)
            call.respondHtmlTemplate(LayoutTemplate(DetailReserveTemplate(reserve!!, userName, roomName))) {
            }
        }
        get("reserves/delete/{id}") {
            val id = call.parameters["id"]!!
            //val idUser = reserveDaoRepository.getItem(id.toInt())!!.idUser
            val idUser = reserveDaoRepository.getUserFromReserve(id.toInt())
            reserveDaoRepository.deleteItem(id.toInt())
            call.respondRedirect("../../users/$idUser")
        }

        //post neccesary to post the data from the input
        post("reserve-action-page") {
            var startTimeStamp: LocalDateTime = LocalDateTime.now()
            var endTimeStamp: LocalDateTime = LocalDateTime.now()
            var idRoom: Int = -1
            var idUser: Int= -1

            val data = call.receiveMultipart()
            data.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "start" -> {
                                startTimeStamp = formatter.formatToDateTime(part.value)
                            }
                            "end" -> {
                                endTimeStamp = formatter.formatToDateTime(part.value)
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

            val reserve = ReserveInsertData(startTimeStamp.toString(), endTimeStamp.toString(), idRoom, idUser) //pass all parameters to create the new Reserve
            if (reserveDaoRepository.verifyReserve(reserve)) {
                reserveDaoRepository.addItem(reserve)
                call.respondRedirect("users/${idUser}")
            }else call.respondRedirect("reserves/new")

        }

        get("users") {
            val listUsers = userDaoRepository.getItemList()
            call.respondHtmlTemplate(LayoutTemplate(AllUsersTemplate(listUsers))) {
            }
        }
        get("users/{id}") {
            val id = call.parameters["id"]!!
            val user = userDaoRepository.getItem(id.toInt())
            val reserves = reserveDaoRepository.getItemListOldByUser(id.toInt())
            val reservesActives = reserveDaoRepository.getItemListActiveByUser(id.toInt())
            call.respondHtmlTemplate(LayoutTemplate(DetailUserTemplate(user!!, reserves, reservesActives))) {
            }
        }

        get("users/delete/{id}") {
            val id = call.parameters["id"]!!
            userDaoRepository.deleteItem(id.toInt())
            val action = Action("delete", LocalDateTime.now().toString())
            fileRepo.listActions += action
            fileRepo.writeFile()
            call.respondRedirect("../../users")
        }

        get("users/new") {
            call.respondHtmlTemplate(LayoutTemplate(AddUserTemplate())) {
            }
        }
        //post neccesary to post the data from the input
        post("user-action-page") {
            var name: String = ""
            var fileName: String = ""

            val data = call.receiveMultipart()
            data.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
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

            val user = UserInsertData(name, fileName) //pass all parameters to create the new User
            userDaoRepository.addItem(user)
            val action = Action("add", LocalDateTime.now().toString())
            fileRepo.listActions += action
            fileRepo.writeFile()
            val idNewUser = userDaoRepository.findIdByName(name)
            call.respondRedirect("users/${idNewUser}")
        }
        //this get is to see the image
        get("/uploads/{imageName}") {
            val imageName = call.parameters["imageName"]
            var file = File("./uploads/$imageName")
            if(file.exists()) call.respondFile(File("./uploads/$imageName"))
            else call.respondText("Image not found", status = HttpStatusCode.NotFound)
        }
    }


}