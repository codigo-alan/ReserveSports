package com.example.models.user

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserDaoRepository () {

    fun getOwnReserves() {
        //TODO NOT IMPLEMENTED yet
    }
    fun getItemList() = transaction {
        UserDaoTable.selectAll().map(::dbToModel)
    }

    fun getItem(userId: Int) = transaction {
        UserDaoTable.select { UserDaoTable.id eq userId }.map(::dbToModel).firstOrNull()
    }

    fun addItem(newUser: User) = transaction{
        UserDaoTable.insert {
            it[id] = newUser.id
            it[name] = newUser.name
            it[profileImg] = newUser.profileImg
        }
    }

    private fun dbToModel(resultRow: ResultRow): User =
        User(resultRow[UserDaoTable.id], resultRow[UserDaoTable.name], resultRow[UserDaoTable.profileImg])
}