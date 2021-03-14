package com.xvadsan.blankmvp.base.extensions

import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

fun String?.toLocalDateTimeOrNull(): LocalDateTime? =
    if (this != null) OffsetDateTime.parse(this).toLocalDateTime() else null

fun String.toLocalDateTime(): LocalDateTime = OffsetDateTime.parse(this).toLocalDateTime()

fun LocalDateTime.getDateTimeOfPattern(pattern: String = "dd LLLL YYYY, HH:mm") =
    DateTimeFormatter.ofPattern(pattern)
        .format(this)

fun LocalDateTime.getDateOfPattern(pattern: String = "dd LLLL") =
    DateTimeFormatter.ofPattern(pattern)
        .format(this)

fun LocalDateTime.getTimeOfPattern(pattern: String = "HH:mm") = DateTimeFormatter.ofPattern(pattern)
    .format(this)

fun Double.format(digits: Int) = "%.${digits}f".format(Locale.ENGLISH, this)

fun Float.format(digits: Int) = "%.${digits}f".format(Locale.ENGLISH, this)

fun Long.toTimeForVoiceMessage(): String {
    val minutes = (this / (60 * 1000)).toInt()
    val seconds = (this / 1000 % 60).toInt()
    return String.format("%d:%02d", minutes, seconds)
}