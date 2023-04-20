package com.example.models.user


import com.example.models.Role
import com.example.models.reserve.ReserveDaoTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class UserDaoRepository() {

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
            it[password] = newUser.password
            it[profileImg] = newUser.profileImg
            it[role] = newUser.role.toString()
        }
    }

    fun findIdByName(name: String) = transaction {

        UserDaoTable.slice(UserDaoTable.id).select { UserDaoTable.name eq name }.last()[UserDaoTable.id]

    }

    fun findIdByNameAndPassword(name: String, password: String) = transaction {

        UserDaoTable.slice(
            UserDaoTable.id
        ).select {
            UserDaoTable.name eq name and( UserDaoTable.password eq password )
        }.count()

    }

    fun findIdByNameAndPassword2(name: String, password: String) = transaction {

        UserDaoTable.slice(UserDaoTable.id).select { UserDaoTable.name eq name and( UserDaoTable.password eq password ) }.last()[UserDaoTable.id]

    }

    fun deleteItem(userId: Int) = transaction {
        ReserveDaoTable.deleteWhere { ReserveDaoTable.idUser eq userId }
        UserDaoTable.deleteWhere { UserDaoTable.id eq userId }
    }

    private fun dbToModel(resultRow: ResultRow): User =
        User(
            resultRow[UserDaoTable.id],
            resultRow[UserDaoTable.name],
            resultRow[UserDaoTable.password],
            resultRow[UserDaoTable.profileImg],
            convertRole(resultRow[UserDaoTable.role])
        )

    private fun convertRole(stringRole: String): Role {
        return if (stringRole == "ADMIN") {
            Role.ADMIN
        }else{
            Role.ENDUSER
        }
    }

}