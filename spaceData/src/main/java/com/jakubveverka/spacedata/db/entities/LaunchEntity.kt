package com.jakubveverka.spacedata.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LaunchEntity(
    @PrimaryKey val id: String,
    val name: String,
    val date: Long, //date_unix
    val success: Boolean,
    val flightNumber: Int,
    val details: String
)