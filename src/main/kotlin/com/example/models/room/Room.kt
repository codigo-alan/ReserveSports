package com.example.models.room

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class Room(val id: @Contextual EntityID<Int>, var name: String, var description: String, var image: String)

data class RoomInsertData(var name: String, var description: String, var image: String)

object RoomDaoTable: IntIdTable() {
    var name = varchar("name", 50)
    var description = varchar("description", 150)
    var image = varchar("image", 150)
}