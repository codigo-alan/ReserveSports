package com.example.models.user

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class User(val id: @Contextual EntityID<Int>, var name: String, var password: String, var profileImg: String)

data class UserInsertData(var name: String, var password: String, var profileImg: String)

object UserDaoTable: IntIdTable(){
    var name = varchar("name", 20)
    var password = varchar("password", 20)
    var profileImg = varchar("profileImg", 150)
}