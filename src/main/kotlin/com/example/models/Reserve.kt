package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Reserve(
    val id: Int,
    var startTimestamp: String,
    var endTimestamp: String,
    var idRoom: Int,
    var idUser: Int
    )

object ReserveDaoTable: Table() {
    val id = integer("id")
    var startTimestamp = varchar("startTimestamp", 150)
    var endTimestamp = varchar("endTimestamp", 150)
    var idRoom = integer("idRoom").references(RoomDaoTable.id)
    var idUser = integer("idUser").references(UserDaoTable.id)
    override val primaryKey = PrimaryKey(id, name = "pk_reserve")
}