package com.example.models.reserve

import com.example.models.Formatter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReserveDaoRepository {
    private val formatter = Formatter()
    fun verifyReserve(newReserve: Reserve): Boolean { //TODO not works properly
        val startDateTime = formatter.formatToDateTime(newReserve.startDateTime)//formatToDateTime(newReserve.startDateTime)
        val endDateTime = formatter.formatToDateTime(newReserve.endDateTime)//formatToDateTime(newReserve.endDateTime)
        val listOfReserves = getItemList()

        for (reserve in listOfReserves) {
            val columnStartTime = formatter.formatToDateTime(reserve.startDateTime)
            val columnEndTime = formatter.formatToDateTime(reserve.endDateTime)
            if (newReserve.idRoom == reserve.idRoom) {
                //start or end between
                if ((startDateTime.isAfter(columnStartTime) && startDateTime.isBefore(columnEndTime)) ||
                    (endDateTime.isAfter(columnStartTime) && endDateTime.isBefore(columnEndTime))
                ) {
                    return false
                }
                //start or end equals
                if ((startDateTime.isEqual(columnStartTime) || startDateTime.isEqual(columnEndTime)) ||
                    (endDateTime.isEqual(columnStartTime) || endDateTime.isEqual(columnEndTime))
                ) {
                    return false
                }
                //start before and end after
                if (startDateTime.isBefore(columnStartTime) && endDateTime.isAfter(columnEndTime)) {
                    return false
                }
            }
        }
        return true
    }
    private fun getItemList() = transaction {
        ReserveDaoTable.selectAll().map(::dbToModel)
    }

    fun getItemListByRoom(idRoom: Int) = transaction {
        ReserveDaoTable.select{ ReserveDaoTable.idRoom eq idRoom }.map(::dbToModel)
    }
    fun getItemListByUser(idUser: Int) = transaction {
        ReserveDaoTable.select{ ReserveDaoTable.idUser eq idUser }.map(::dbToModel)
    }
    fun getItemListActiveByUser(idUser: Int) = transaction {
        ReserveDaoTable.select{
            (ReserveDaoTable.idUser eq idUser) and
                    (ReserveDaoTable.endDateTime greaterEq LocalDateTime.now()) }.map(::dbToModel)
    }

    fun getItem(reserveId: Int) = transaction {
        ReserveDaoTable.select { ReserveDaoTable.id eq reserveId }.map(::dbToModel).firstOrNull()
    }

    fun deleteItem(reserveId: Int) = transaction {
        println(reserveId)
        ReserveDaoTable.deleteWhere { ReserveDaoTable.id eq reserveId }
    }

    fun addItem(newReserve: Reserve)= transaction {
        ReserveDaoTable.insert {
            it[id] = newReserve.id
            it[startDateTime] =
                formatter.formatToDateTime(newReserve.startDateTime)//formatToDateTime(newReserve.startDateTime)
            it[endDateTime] =
                formatter.formatToDateTime(newReserve.endDateTime)//formatToDateTime(newReserve.endDateTime)
            it[idRoom] = newReserve.idRoom
            it[idUser] = newReserve.idUser

        }
    }

    private fun dbToModel(resultRow: ResultRow): Reserve =
        Reserve(resultRow[ReserveDaoTable.id], resultRow[ReserveDaoTable.startDateTime].toString(), resultRow[ReserveDaoTable.endDateTime].toString(),
            resultRow[ReserveDaoTable.idRoom], resultRow[ReserveDaoTable.idUser])
}