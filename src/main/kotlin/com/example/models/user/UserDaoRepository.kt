package com.example.models.user

import com.example.models.room.RoomDaoTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserDaoRepository () {
    fun getItemList() = transaction {
        UserDaoTable.selectAll().map(::dbToModel)
    }

    fun getItem(userId: Int) = transaction {
        UserDaoTable.select { UserDaoTable.id eq userId }.map(::dbToModel).firstOrNull()
    }

    private fun dbToModel(resultRow: ResultRow): User =
        User(resultRow[UserDaoTable.id], resultRow[UserDaoTable.name])
}