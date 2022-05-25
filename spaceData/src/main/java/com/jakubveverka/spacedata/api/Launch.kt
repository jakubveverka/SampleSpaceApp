package com.jakubveverka.spacedata.api

data class Launch(
    val id: String,
    val name: String,
    val date_unix: Long, //date_unix
    val success: Boolean,
    val flight_number: Int,
    val details: String
)
