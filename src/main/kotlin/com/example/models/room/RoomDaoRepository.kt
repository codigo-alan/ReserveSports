package com.example.models.room

import com.example.models.room.RoomDaoTable
import com.example.models.user.UserDaoTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class RoomDaoRepository {

    fun getItemList() = transaction {
        RoomDaoTable.selectAll().map(::dbToModel)
    }

    fun getItem(roomId: Int) = transaction {
        RoomDaoTable.select { RoomDaoTable.id eq roomId }.map(::dbToModel).firstOrNull()
    }

    fun addItem(newRoom: RoomInsertData) = transaction{
        RoomDaoTable.insert {
            it[name] = newRoom.name
            it[description] = newRoom.description
            it[image] = newRoom.image
        }
    }

    private fun dbToModel(resultRow: ResultRow): Room =
        Room(resultRow[RoomDaoTable.id], resultRow[RoomDaoTable.name], resultRow[RoomDaoTable.description], resultRow[RoomDaoTable.image])

}