package com.example.models.room

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Room(val id: Int, var name: String, var description: String, var image: String)

object RoomDaoTable: Table() {
    val id = integer("id")
    var name = varchar("name", 50)
    var description = varchar("description", 150)
    var image = varchar("image", 150)
    override val primaryKey = PrimaryKey(id, name = "pk_room")
}