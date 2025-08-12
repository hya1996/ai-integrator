@file:OptIn(ExperimentalTime::class, FormatStringsInDatetimeFormats::class)

package com.ai.integrator.core.framework.time

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

private  val localTimeZone by lazy { TimeZone.of("UTC+8") }

val defaultDateTimeFormat = LocalDateTime.Format {
    byUnicodePattern("yyyy-MM-dd HH:mm")
}

fun Long.toDateTimeFormat(
    format: DateTimeFormat<LocalDateTime> = defaultDateTimeFormat
): String {
    val instant = Instant.fromEpochMilliseconds(this)
    return instant.toLocalDateTime(localTimeZone).format(format)
}