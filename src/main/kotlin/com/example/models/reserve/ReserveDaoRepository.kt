package com.example.models.reserve

import com.example.models.user.UserDaoTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ReserveDaoRepository {
    fun getItemList() = transaction {
        ReserveDaoTable.selectAll().map(::dbToModel)
    }

    fun getItem(reserveId: Int) = transaction {
        ReserveDaoTable.select { ReserveDaoTable.id eq reserveId }.map(::dbToModel).firstOrNull()
    }

    fun addItem(newReserve: Reserve) = transaction{
        ReserveDaoTable.insert {
            it[id] = newReserve.id
            it[startTimestamp] = newReserve.startTimestamp
            it[endTimestamp] = newReserve.endTimestamp
            it[idRoom] = newReserve.idRoom
            it[idUser] = newReserve.idUser
        }
    }

    private fun dbToModel(resultRow: ResultRow): Reserve =
        Reserve(resultRow[ReserveDaoTable.id], resultRow[ReserveDaoTable.startTimestamp], resultRow[ReserveDaoTable.endTimestamp],
            resultRow[ReserveDaoTable.idRoom], resultRow[ReserveDaoTable.idUser])
}