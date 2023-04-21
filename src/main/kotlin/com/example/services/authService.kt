package com.example.services

import com.example.models.Role
import com.example.models.user.User

object AuthService {

    lateinit var user: User
    val roles = listOf<String>((Role.ADMIN).toString(), (Role.ENDUSER).toString())

    fun changeUser(user: User){
        this.user = user
    }
}