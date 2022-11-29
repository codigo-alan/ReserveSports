package com.example.models.user

import com.example.models.room.RoomDaoTable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(val id: Int, var name: String)

@Serializable
data class UserInsertData(var name: String)

object UserDaoTable: Table(){
    val id = integer("id").autoIncrement()
    var name = varchar("name", 50)
    override val primaryKey: PrimaryKey = PrimaryKey(UserDaoTable.id, name = "pk_user")
}