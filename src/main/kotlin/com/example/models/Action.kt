package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Action(val type: String, val time: String)