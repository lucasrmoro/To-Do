package br.com.lucas.santanderbootcamp.todolist.core.extensions

import com.google.android.material.timepicker.MaterialTimePicker

fun MaterialTimePicker.getHourFormatted() : String{
    val hours = if (hour in 0..9) "0$hour" else hour
    val minutes = if (minute in 0..9) "0$minute" else minute
    return "$hours:$minutes"
}