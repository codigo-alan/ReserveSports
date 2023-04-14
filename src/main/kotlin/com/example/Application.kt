package com.example

import com.example.models.UserSession
import com.example.models.reserve.ReserveDaoTable
import com.example.models.room.RoomDaoTable
import com.example.models.user.UserDaoTable
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
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
    install(Authentication) {
        session<UserSession>("auth-session") {
            validate { session ->
                if(session.name.startsWith("alan")) {
                    session
                } else {
                    null
                }
            }
            challenge {
                call.respondRedirect("/")
            }

        }
        form("auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                if (credentials.name == "alan" && credentials.password == "123456Aa") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
            challenge {
                call.respond(HttpStatusCode.Unauthorized, "Credentials are not valid")
            }
        }
    }
    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60
        }
    }


}
