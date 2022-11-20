package com.example.models

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class RoomDaoRepository {

    fun getItemList() = transaction {
        RoomDaoTable.selectAll().map(::dbToModel)
    }

    private fun dbToModel(resultRow: ResultRow): Room =
        Room(resultRow[RoomDaoTable.id], resultRow[RoomDaoTable.name], resultRow[RoomDaoTable.description], resultRow[RoomDaoTable.image])

    fun getItem(roomId: Int) = transaction {
        RoomDaoTable.select { RoomDaoTable.id eq roomId }.map(::dbToModel).firstOrNull()
    }

}