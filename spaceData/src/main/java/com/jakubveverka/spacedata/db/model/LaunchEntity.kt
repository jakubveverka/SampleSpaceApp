package com.jakubveverka.spacedata.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jakubveverka.spacedata.domain.model.Launch

@Entity
data class LaunchEntity(
    @PrimaryKey val id: String,
    val name: String,
    val date: Long?,
    val success: Boolean,
    @ColumnInfo(name = "flight_number") val flightNumber: Int,
    val details: String?
)

fun LaunchEntity.toDomain() = Launch(id, name, date, success, flightNumber, details)

fun List<LaunchEntity>.toDomain() = map { it.toDomain() }