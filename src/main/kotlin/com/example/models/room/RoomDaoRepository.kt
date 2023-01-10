package com.example.models.room

import com.example.models.reserve.ReserveDaoTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
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

    fun deleteItem(roomId: Int) = transaction {
        ReserveDaoTable.deleteWhere { ReserveDaoTable.idRoom eq roomId }
        RoomDaoTable.deleteWhere { RoomDaoTable.id eq roomId }
    }

    fun updateItem(roomId: Int, updatedRoom: RoomInsertData) = transaction {
        RoomDaoTable.update({ RoomDaoTable.id eq roomId }){
            it[name] = updatedRoom.name
            it[description] = updatedRoom.description
            it[image] = updatedRoom.image
        }
    }

    private fun dbToModel(resultRow: ResultRow): Room =
        Room(resultRow[RoomDaoTable.id], resultRow[RoomDaoTable.name], resultRow[RoomDaoTable.description], resultRow[RoomDaoTable.image])

}