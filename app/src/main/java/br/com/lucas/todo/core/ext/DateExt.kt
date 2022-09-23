package br.com.lucas.todo.core.ext

import br.com.lucas.todo.core.Constants.BR
import br.com.lucas.todo.core.Constants.DEFAULT_DATE_PATTERN
import br.com.lucas.todo.core.Constants.PT
import br.com.lucas.todo.core.Constants.UTC
import java.text.SimpleDateFormat
import java.util.*

private val locale = Locale(PT, BR)

fun String.formatStringDateToFullDate(currentPattern: String = DEFAULT_DATE_PATTERN): String {
    val currentDate = SimpleDateFormat(currentPattern, locale).parse(this) ?: Date()
    return SimpleDateFormat("EEE d, MMM ''yy", locale).format(currentDate)
}

fun Long.formatLongToStringDate(pattern: String = DEFAULT_DATE_PATTERN): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(pattern, locale).apply {
        timeZone = TimeZone.getTimeZone(UTC)
    }
    return formatter.format(date)
}

fun String.formatStringDateToLong(pattern: String = DEFAULT_DATE_PATTERN): Long =
    SimpleDateFormat(pattern, locale).parse(this)?.time ?: 0