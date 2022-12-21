package com.jakubveverka.spacelaunch.uimodel

import com.jakubveverka.spacedata.domain.model.Launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

data class LaunchUi(
    val id: String,
    val name: String,
    val date: String?,
    val success: Boolean,
    val flightNumber: Int,
    val details: String?
)

fun Launch.toUIModel() = LaunchUi(
    id,
    name,
    date?.let {
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.getDefault())
        val datetime = Instant.fromEpochMilliseconds(it * 1000).toLocalDateTime(TimeZone.currentSystemDefault())
        datetime.toJavaLocalDateTime().format(formatter) // TODO to TimeUtil extensions
    },
    success,
    flightNumber,
    details
)

fun List<Launch>.toUIModel() = map { it.toUIModel() }