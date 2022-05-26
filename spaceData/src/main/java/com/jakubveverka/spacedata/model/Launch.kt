package com.jakubveverka.spacedata.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Launch(
    @SerializedName("id") @PrimaryKey val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("date_unix") val date: Long?,
    @SerializedName("success") val success: Boolean,
    @SerializedName("flight_number") val flightNumber: Int,
    @SerializedName("details") val details: String?
)
