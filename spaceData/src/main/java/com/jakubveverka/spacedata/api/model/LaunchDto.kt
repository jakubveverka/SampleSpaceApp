package com.jakubveverka.spacedata.api.model

import com.google.gson.annotations.SerializedName
import com.jakubveverka.spacedata.db.model.LaunchEntity
import com.jakubveverka.spacedata.domain.model.Launch

data class LaunchDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("date_unix") val date: Long?,
    @SerializedName("success") val success: Boolean,
    @SerializedName("flight_number") val flightNumber: Int,
    @SerializedName("details") val details: String?
)

fun LaunchDto.toDomain() = Launch(id, name, date, success, flightNumber, details)

fun List<LaunchDto>.toDomain() = map { it.toDomain() }

fun LaunchDto.toDbEntity() = LaunchEntity(id, name, date, success, flightNumber, details)

fun List<LaunchDto>.toDbEntity() = map { it.toDbEntity() }