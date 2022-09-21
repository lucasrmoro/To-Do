package br.com.lucas.todo.core.extensions

import java.text.SimpleDateFormat
import java.util.*

private val locale = Locale("pt", "BR")

fun Date.formatDateToString(): String {
    return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}

fun Long.convertLongToCompactDate(): String {
    return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}

fun Long.convertLongToFullDate(): String {
    return SimpleDateFormat("EEE d, MMM ''yy", locale).format(this)
}

fun String.convertStringToLong(): Long {
    return SimpleDateFormat("dd/MM/yyyy").parse(this).time
}