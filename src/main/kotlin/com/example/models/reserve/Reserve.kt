package com.example.models.reserve

import com.example.models.room.RoomDaoTable
import com.example.models.user.UserDaoTable
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

@Serializable
data class Reserve(
    val id: Int,
    var startDateTime: String,
    var endDateTime: String,
    var idRoom: Int,
    var idUser: Int
    )

data class ReserveInsertData(
    var startDateTime: String,
    var endDateTime: String,
    var idRoom: Int,
    var idUser: Int
)

object ReserveDaoTable: Table() {
    val id = integer("id")
    var startDateTime = datetime("startTimestamp")
    var endDateTime = datetime("endTimestamp")
    var idRoom = integer("idRoom").references(RoomDaoTable.id)
    var idUser = integer("idUser").references(UserDaoTable.id)
    override val primaryKey = PrimaryKey(id, name = "pk_reserve")
}