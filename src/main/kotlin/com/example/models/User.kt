package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(val id: Int, var name: String)

object UserDaoTable: Table(){
    val id = integer("id")
    var name = varchar("name", 50)
}