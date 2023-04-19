package com.example.services

import com.example.models.user.User

class AuthService private constructor(){

    lateinit var user: User

    companion object{
        val authService: AuthService by lazy { AuthService() }
    }
}