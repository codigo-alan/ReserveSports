package com.example.models.user

import org.jetbrains.exposed.sql.*
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

    fun addItem(newUser: UserInsertData) = transaction{
        UserDaoTable.insert {
            //it[id] = newUser.id
            it[name] = newUser.name
            it[profileImg] = newUser.profileImg
        }
    }

    fun findIdByName(name: String) = transaction {

        UserDaoTable.slice(UserDaoTable.id).select { UserDaoTable.name eq name }.last()[UserDaoTable.id]

    }

    private fun dbToModel(resultRow: ResultRow): User =
        User(resultRow[UserDaoTable.id], resultRow[UserDaoTable.name], resultRow[UserDaoTable.profileImg])
}