package br.com.lucas.todo.core.extensions

import kotlin.math.absoluteValue

fun Int.convertIntTimeToString(): String {
    val hours = this / 60
    var minutes = hours * 60 - this
    minutes = minutes.absoluteValue

    val hoursText = if (hours in 0..9) "0$hours" else hours
    val minutesText = if (minutes in 0..9) "0$minutes" else minutes
    return "$hoursText:$minutesText"
}