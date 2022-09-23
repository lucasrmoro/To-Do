package br.com.lucas.todo.core.ext

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

fun String.formatHoursAndMinutesToIntTime(): Int {
    val entireTime = split(':')
    val hours = entireTime[0].toInt()
    val minutes = entireTime[1].toInt()
    val hoursInMinutes = hours * 60

    return hoursInMinutes + minutes
}

fun MaterialTimePicker.getHoursAndMinutesFormatted() = String.format("%02d:%02d", hour, minute)