package com.example.models.reserve

import com.example.models.Formatter
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReserveDaoRepository {
    private val formatter = Formatter()
    fun verifyReserve(newReserve: Reserve): Boolean { //TODO need test
        //var isCorrect: Boolean = false
        val startDateTime = formatter.formatToDateTime(newReserve.startDateTime)//formatToDateTime(newReserve.startDateTime)
        val endDateTime = formatter.formatToDateTime(newReserve.endDateTime)//formatToDateTime(newReserve.endDateTime)
        for (resultRow in ReserveDaoTable.selectAll()) {
            val columnStartTime = resultRow[ReserveDaoTable.startDateTime]
            val columnEndTime = resultRow[ReserveDaoTable.endDateTime]
            if (newReserve.idRoom == resultRow[ReserveDaoTable.idRoom]) {
                println(newReserve.id)
                println(resultRow[ReserveDaoTable.id])
                //start or end between
                if ((startDateTime.isAfter(columnStartTime) && startDateTime.isBefore(columnEndTime)) ||
                    (endDateTime.isAfter(columnStartTime) && endDateTime.isBefore(columnEndTime))){
                    return false
                }
                //start or end equals
                if ((startDateTime.isEqual(columnStartTime) || startDateTime.isEqual(columnEndTime)) ||
                    (endDateTime.isEqual(columnStartTime) || endDateTime.isEqual(columnEndTime))){
                    return false
                }
                //start before and end after
                if (startDateTime.isBefore(columnStartTime) && endDateTime.isAfter(columnEndTime)){
                    return false
                }

            }
        }
        return true
    }
    fun getItemList() = transaction {
        ReserveDaoTable.selectAll().map(::dbToModel)
    }

    fun getItemListByRoom(idRoom: Int) = transaction {
        ReserveDaoTable.select{ ReserveDaoTable.idRoom eq idRoom }.map(::dbToModel)
    }
    fun getItemListByUser(idUser: Int) = transaction {
        ReserveDaoTable.select{ ReserveDaoTable.idUser eq idUser }.map(::dbToModel)
    }

    fun getItem(reserveId: Int) = transaction {
        ReserveDaoTable.select { ReserveDaoTable.id eq reserveId }.map(::dbToModel).firstOrNull()
    }

    fun addItem(newReserve: Reserve)= transaction {
        /*val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val textStartDateTime = newReserve.startDateTime.replace('T',' ')
        val textEndDateTime = newReserve.endDateTime.replace('T',' ')*/
        if (verifyReserve(newReserve)) {

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
    }

    /*private fun formatToDateTime(text: String): LocalDateTime{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val readyText = text.replace('T',' ')
        return LocalDateTime.parse(readyText, formatter)
    }*/

    private fun dbToModel(resultRow: ResultRow): Reserve =
        Reserve(resultRow[ReserveDaoTable.id], resultRow[ReserveDaoTable.startDateTime].toString(), resultRow[ReserveDaoTable.endDateTime].toString(),
            resultRow[ReserveDaoTable.idRoom], resultRow[ReserveDaoTable.idUser])
}