package com.jakubveverka.spacedata.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Launch(
    @Json(name = "id") @PrimaryKey val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "date_unix") val date: Long, //date_unix
    @Json(name = "success") val success: Boolean,
    @Json(name = "flight_number") val flightNumber: Int,
    @Json(name = "details") val details: String?
)
