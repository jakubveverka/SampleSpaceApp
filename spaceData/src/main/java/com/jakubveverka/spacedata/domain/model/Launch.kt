package com.jakubveverka.spacedata.domain.model

data class Launch(
    val id: String,
    val name: String,
    val date: Long?,
    val success: Boolean,
    val flightNumber: Int,
    val details: String?
)