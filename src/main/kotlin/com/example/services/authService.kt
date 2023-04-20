package com.example.services

import com.example.models.user.User

object AuthService {

    lateinit var user: User

    fun changeUser(user: User){
        this.user = user
    }
}