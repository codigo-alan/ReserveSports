package com.example

import com.example.models.ReserveDaoTable
import com.example.models.RoomDaoTable
import com.example.models.UserDaoTable
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {

    Database.connect("jdbc:postgresql://localhost:5432/sports", driver = "org.postgresql.Driver", user = "sports", password = "sports")//Para usar en ordenador Alan
    //Database.connect("jdbc:postgresql://localhost:5432/sports", driver = "org.postgresql.Driver", user = "sjo") //Para usar en ITB

    transaction {
        addLogger(StdOutSqlLogger)
        //createTable if not exists
        SchemaUtils.create(RoomDaoTable)
        SchemaUtils.create(UserDaoTable)
        SchemaUtils.create(ReserveDaoTable)

    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
