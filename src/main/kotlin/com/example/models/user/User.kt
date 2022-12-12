package com.example.models.user

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(val id: Int, var name: String, var profileImg: String)

@Serializable
data class UserInsertData(var name: String, var profileImg: String)

object UserDaoTable: Table(){
    val id = integer("id").autoIncrement().uniqueIndex()
    var name = varchar("name", 50)
    var profileImg = varchar("profileImg", 150)
    override val primaryKey: PrimaryKey = PrimaryKey(UserDaoTable.id, name = "pk_user")
}