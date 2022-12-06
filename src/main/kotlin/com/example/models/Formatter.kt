package com.example.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Formatter {
    fun formatToDateTime(text: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val readyText = text.replace('T',' ')
        return LocalDateTime.parse(readyText, formatter)
    }
}