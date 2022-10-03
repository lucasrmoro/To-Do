package br.com.lucas.todo.core.util

import br.com.lucas.todo.core.Constants
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private val locale = Locale(Constants.PT, Constants.BR)

    fun formatLongToStringDate(
        longDate: Long?,
        pattern: String = Constants.DEFAULT_DATE_PATTERN
    ): String {
        return longDate?.let {
            try {
                val date = Date(it)
                val formatter = SimpleDateFormat(pattern, locale).apply {
                    timeZone = TimeZone.getTimeZone(Constants.UTC)
                }
                formatter.format(date) ?: ""
            } catch (e: NumberFormatException) {
                Timber.e(e)
                ""
            }
        } ?: ""
    }

    fun formatStringDateToLong(
        dateString: String?,
        pattern: String = Constants.DEFAULT_DATE_PATTERN
    ): Long =
        dateString?.let {
            try {
                if (it.isNotEmpty()) SimpleDateFormat(pattern, locale).parse(it)?.time else 0
            } catch (e: ParseException) {
                Timber.e(e)
                Long.MIN_VALUE
            }
        } ?: Long.MIN_VALUE
}