package br.com.lucas.santanderbootcamp.todolist.core.extensions

import java.text.SimpleDateFormat
import java.util.*

private val locale = Locale("pt", "BR")

fun Date.formatDateToString(): String {
    return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}

fun Long.convertLongToDate(): String {
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(this)
}

fun String.convertStringToLong(): Long {
    val df = SimpleDateFormat("dd/MM/yyyy")
    return df.parse(this).time
}