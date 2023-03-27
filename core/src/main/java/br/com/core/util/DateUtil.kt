package br.com.core.util

import android.text.format.DateUtils
import br.com.core.Constants
import br.com.core.R
import br.com.core.providers.ResourcesProviderInterface
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateUtil @Inject constructor(
    private val res: ResourcesProviderInterface
) {

    private val locale = Locale(Constants.PT, Constants.BR)

    fun getStringDateBy(day: String?, month: String?, year: String?) =
        try {
            if (day != null && month != null && year != null) {
                "$day/$month/$year"
            } else throw NullPointerException("Cant get stringDate by day/month/year, some parameter is null")
        } catch (e: NullPointerException) {
            Timber.e(e)
            ""
        }

    fun getYearsFrom(listOfDateString: List<String>?) =
        listOfDateString?.map { getYear(it) }?.toSet()?.toList()

    fun getMonthsFrom(listOfDateString: List<String>?, desiredYear: String?) =
        listOfDateString?.filter { date -> isMonthOf(date, desiredYear) }?.map { getMonth(it) }
            ?.toSet()
            ?.toList()

    fun getDaysFrom(listOfDateString: List<String>?, desiredMonth: String?, desiredYear: String?) =
        listOfDateString?.filter { date -> isDayOf(date, desiredMonth, desiredYear) }
            ?.map { getDay(it) }

    fun getMonthNameFrom(monthNumber: String): String =
        when (monthNumber) {
            Constants.JANUARY_MONTH_NUMBER -> res.getString(R.string.january)
            Constants.FEBRUARY_MONTH_NUMBER -> res.getString(R.string.february)
            Constants.MARCH_MONTH_NUMBER -> res.getString(R.string.march)
            Constants.APRIL_MONTH_NUMBER -> res.getString(R.string.april)
            Constants.MAY_MONTH_NUMBER -> res.getString(R.string.may)
            Constants.JUNE_MONTH_NUMBER -> res.getString(R.string.june)
            Constants.JULY_MONTH_NUMBER -> res.getString(R.string.july)
            Constants.AUGUST_MONTH_NUMBER -> res.getString(R.string.august)
            Constants.SEPTEMBER_MONTH_NUMBER -> res.getString(R.string.september)
            Constants.OCTOBER_MONTH_NUMBER -> res.getString(R.string.october)
            Constants.NOVEMBER_MONTH_NUMBER -> res.getString(R.string.november)
            Constants.DECEMBER_MONTH_NUMBER -> res.getString(R.string.december)
            else -> ""
        }

    fun getFormattedYear(date: String?): String {
        return when {
            isPastYear(date) -> res.getString(R.string.past_year)
            isNextYear(date) -> res.getString(R.string.next_year)
            else -> getYear(date)
        }
    }

    fun getFormattedMonthFrom(month: String?, year: String?): String? = when {
        isPastMonth(month, year) -> res.getString(R.string.past_month)
        isNextMonth(month, year) -> res.getString(R.string.next_month)
        else -> month?.let { getMonthNameFrom(it) }
    }

    fun isPastMonth(month: String?, year: String?) =
        try {
            if (month?.isNotEmpty() == true && year?.isNotEmpty() == true) {
                val currentMonth =
                    getMonth(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()
                val currentYear = getYear(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()

                month.toInt() == (currentMonth - 1) && year.toInt() == currentYear
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e)
            false
        }

    fun isNextMonth(month: String?, year: String?) =
        try {
            if (month?.isNotEmpty() == true && year?.isNotEmpty() == true) {
                val currentMonth =
                    getMonth(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()
                val currentYear = getYear(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()

                month.toInt() == (currentMonth + 1) && year.toInt() == currentYear
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e)
            false
        }

    fun isCurrentYear(year: String?) =
        try {
            if (year?.isNotEmpty() == true) {
                val currentYear = getYear(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()

                year.toInt() == currentYear
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e)
            false
        }

    fun isCurrentMonth(month: String?, year: String?) =
        try {
            if (month?.isNotEmpty() == true && year?.isNotEmpty() == true) {
                val currentMonth =
                    getMonth(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()
                val currentYear = getYear(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()

                month.toInt() == currentMonth && year.toInt() == currentYear
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e)
            false
        }

    fun getFormattedDay(
        day: String?,
        month: String?,
        year: String?,
        concatDayString: Boolean = true
    ): String? {
        val date = getStringDateBy(day, month, year)
        return when {
            isYesterday(date) -> res.getString(R.string.yesterday)
            isToday(date) -> res.getString(R.string.today)
            isTomorrow(date) -> res.getString(R.string.tomorrow)
            else -> if (concatDayString) {
                res.getString(R.string.day_concat_with_number, day ?: "")
            } else {
                day
            }
        }
    }

    fun getYear(date: String?) =
        date?.let {
            try {
                if (it.isNotEmpty()) it.substringAfterLast('/') else ""
            } catch (e: Exception) {
                Timber.e(e)
                ""
            }
        } ?: ""

    fun isMonthOf(dateToCompare: String?, year: String?) =
        dateToCompare?.let {
            try {
                if (it.isNotEmpty()) it.contains(Regex("$year")) else false
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    fun isDayOf(dateToCompare: String?, month: String?, year: String?) =
        dateToCompare?.let {
            try {
                if (it.isNotEmpty()) it.contains("$month/$year") else false
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    fun getMonth(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) it.substring(3, 5) else ""
            } catch (e: Exception) {
                Timber.e(e)
                ""
            }
        } ?: ""

    fun getDay(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) it.substringBefore('/') else ""
            } catch (e: Exception) {
                Timber.e(e)
                ""
            }
        } ?: ""


    fun isNextYear(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val yearToCompare = getYear(it).toInt()
                    val currentYear = getYear(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()

                    yearToCompare == (currentYear + 1)
                } else {
                    false
                }
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    fun isPastYear(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val yearToCompare = getYear(it).toInt()
                    val currentYear = getYear(formatLongToStringDate(Calendar.getInstance(locale).time.time)).toInt()

                    yearToCompare == (currentYear - 1)
                } else {
                    false
                }
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    fun isYesterday(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val dateToCompare = formatStringDateToLong(it)
                    DateUtils.isToday(dateToCompare + DateUtils.DAY_IN_MILLIS)
                } else {
                    false
                }
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    fun isTomorrow(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val dateToCompare = formatStringDateToLong(it)
                    DateUtils.isToday(dateToCompare - DateUtils.DAY_IN_MILLIS)
                } else {
                    false
                }
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    fun isToday(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val dateToCompare = formatStringDateToLong(it)
                    DateUtils.isToday(dateToCompare)
                } else {
                    false
                }
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    fun formatStringDateToLong(
        stringDate: String?,
        pattern: String = Constants.DEFAULT_DATE_PATTERN
    ): Long =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) SimpleDateFormat(pattern, locale).parse(it)?.time else 0
            } catch (e: ParseException) {
                Timber.e(e)
                Long.MIN_VALUE
            }
        } ?: Long.MIN_VALUE

    fun formatLongToStringDate(
        dateInLong: Long?,
        pattern: String = Constants.DEFAULT_DATE_PATTERN
    ): String {
        return dateInLong?.let {
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
}