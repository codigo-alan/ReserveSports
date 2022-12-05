package com.example.models.reserve

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReserveDaoRepository {
    private fun verifyReserve(newReserve: Reserve): Boolean {
        val startDateTime = formatToDateTime(newReserve.startDateTime)
        val endDateTime = formatToDateTime(newReserve.endDateTime)
        ReserveDaoTable.selectAll().forEach {
            if(it[ReserveDaoTable.startDateTime].isAfter(startDateTime) && it[ReserveDaoTable.endDateTime].isBefore(endDateTime) ){
                return false
            }
        }
        return true
    }
    fun getItemList() = transaction {
        ReserveDaoTable.selectAll().map(::dbToModel)
    }

    fun getItem(reserveId: Int) = transaction {
        ReserveDaoTable.select { ReserveDaoTable.id eq reserveId }.map(::dbToModel).firstOrNull()
    }

    fun addItem(newReserve: Reserve) = transaction{
        /*val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val textStartDateTime = newReserve.startDateTime.replace('T',' ')
        val textEndDateTime = newReserve.endDateTime.replace('T',' ')*/
        if(verifyReserve(newReserve)) {
            ReserveDaoTable.insert {
                it[id] = newReserve.id
                it[startDateTime] = formatToDateTime(newReserve.startDateTime)
                it[endDateTime] = formatToDateTime(newReserve.endDateTime)
                it[idRoom] = newReserve.idRoom
                it[idUser] = newReserve.idUser
            }
        }
    }

    private fun formatToDateTime(text: String): LocalDateTime{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val readyText = text.replace('T',' ')
        return LocalDateTime.parse(readyText, formatter)
    }

    private fun dbToModel(resultRow: ResultRow): Reserve =
        Reserve(resultRow[ReserveDaoTable.id], resultRow[ReserveDaoTable.startDateTime].toString(), resultRow[ReserveDaoTable.endDateTime].toString(),
            resultRow[ReserveDaoTable.idRoom], resultRow[ReserveDaoTable.idUser])
}