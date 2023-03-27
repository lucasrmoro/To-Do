package br.com.uikit.extensions

import com.google.android.material.timepicker.MaterialTimePicker
import kotlin.math.absoluteValue

fun Int.formatIntTimeToHoursAndMinutes(): String {
    val hours = this / 60
    var minutes = hours * 60 - this
    minutes = minutes.absoluteValue

    val hoursText = if (hours in 0..9) "0$hours" else hours
    val minutesText = if (minutes in 0..9) "0$minutes" else minutes
    return "$hoursText:$minutesText"
}

fun formatHoursAndMinutesToIntTime(hour: String?, minute: String?): Int {
    val hours = if (hour?.isEmpty() == true) 0 else hour?.toInt() ?: 0
    val minutes = if(minute?.isEmpty() == true) 0 else minute?.toInt() ?: 0
    val hoursInMinutes = hours * 60

    return hoursInMinutes + minutes
}

fun formatHoursAndMinutesToDisplayableTime(hours: String, minutes: String): String {
    return "$hours:$minutes"
}

fun Int.toHour(): String {
    val hours = this / 60
    return if (hours in 0..9) "0$hours" else hours.toString()
}

fun Int.toMinute(): String {
    val minutes = (((this / 60) * 60) - this).absoluteValue
    return if (minutes in 0..9) "0$minutes" else minutes.toString()
}

fun MaterialTimePicker.getHoursAndMinutesFormatted() = String.format("%02d:%02d", hour, minute)