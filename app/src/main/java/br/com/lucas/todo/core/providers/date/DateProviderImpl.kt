package br.com.lucas.todo.core.providers.date

import android.text.format.DateUtils
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants
import br.com.lucas.todo.core.ext.*
import br.com.lucas.todo.core.providers.resources.ResourcesProvider
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

class DateProviderImpl @Inject constructor(
    private val res: ResourcesProvider
) : DateProvider {

    private val locale = Locale(Constants.PT, Constants.BR)
    private val calendarInstance = Calendar.getInstance(locale)

    override fun toDate(stringDate: String, datePattern: String): Date =
        SimpleDateFormat(datePattern, locale).parse(stringDate)
            ?: throw NullPointerException("Cant get date from string date")

    override fun getStringDateBy(day: String?, month: String?, year: String?) =
        try {
            if (day != null && month != null && year != null) {
                day + Char.BAR + month + Char.BAR + year
            } else throw NullPointerException("Cant get stringDate by day/month/year, some parameter is null")
        } catch (e: NullPointerException) {
            Timber.e(e)
            String.EMPTY
        }

    override fun getYearsFrom(listOfDateString: List<String>?): List<String> =
        listOfDateString?.map { getYear(it) }?.toSet()?.toList().orEmpty()

    override fun getMonthsFrom(
        listOfDateString: List<String>?,
        desiredYear: String?
    ): List<String> =
        listOfDateString?.filter { date -> isMonthOf(date, desiredYear) }?.map { getMonth(it) }
            ?.toSet()
            ?.toList().orEmpty()

    override fun getDaysFrom(
        listOfDateString: List<String>?,
        desiredMonth: String?,
        desiredYear: String?
    ) = listOfDateString?.filter { date -> isDayOf(date, desiredMonth, desiredYear) }
            ?.map { getDay(it) }.orEmpty()

    override fun getMonthNameFrom(monthNumber: String): String =
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
            else -> String.EMPTY
        }

    override fun getFormattedYear(date: String?): String {
        return when {
            isPastYear(date) -> res.getString(R.string.past_year)
            isNextYear(date) -> res.getString(R.string.next_year)
            else -> getYear(date)
        }
    }

    override fun getFormattedMonthFrom(month: String?, year: String?) = when {
        isPastMonth(month, year) -> res.getString(R.string.past_month)
        isNextMonth(month, year) -> res.getString(R.string.next_month)
        else -> month?.let { getMonthNameFrom(it) } ?: String.EMPTY
    }

    override fun isPastMonth(month: String?, year: String?) =
        try {
            if (month?.isNotEmpty() == true && year?.isNotEmpty() == true) {
                val currentMonth =
                    getMonth(formatLongToStringDate(calendarInstance.time.time)).toInt()
                val currentYear =
                    getYear(formatLongToStringDate(calendarInstance.time.time)).toInt()

                month.toInt() == (currentMonth - 1) && year.toInt() == currentYear
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e)
            false
        }

    override fun isNextMonth(month: String?, year: String?) =
        try {
            if (month?.isNotEmpty() == true && year?.isNotEmpty() == true) {
                val currentMonth =
                    getMonth(formatLongToStringDate(calendarInstance.time.time)).toInt()
                val currentYear =
                    getYear(formatLongToStringDate(calendarInstance.time.time)).toInt()

                month.toInt() == (currentMonth + Int.ONE) && year.toInt() == currentYear
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e)
            false
        }

    override fun isCurrentYear(year: String?) =
        try {
            if (year?.isNotEmpty() == true) {
                val currentYear =
                    getYear(formatLongToStringDate(calendarInstance.time.time)).toInt()

                year.toInt() == currentYear
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e)
            false
        }

    override fun isCurrentMonth(month: String?, year: String?) =
        try {
            if (month?.isNotEmpty() == true && year?.isNotEmpty() == true) {
                val currentMonth =
                    getMonth(formatLongToStringDate(calendarInstance.time.time)).toInt()
                val currentYear =
                    getYear(formatLongToStringDate(calendarInstance.time.time)).toInt()

                month.toInt() == currentMonth && year.toInt() == currentYear
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e)
            false
        }

    override fun getFormattedDay(
        day: String?,
        month: String?,
        year: String?,
        concatDayString: Boolean
    ): String {
        val date = getStringDateBy(day, month, year)
        return when {
            isYesterday(date) -> res.getString(R.string.yesterday)
            isToday(date) -> res.getString(R.string.today)
            isTomorrow(date) -> res.getString(R.string.tomorrow)
            else -> if (concatDayString) {
                res.getString(R.string.day_concat_with_number, day ?: String.EMPTY)
            } else {
                day
            }.orEmpty()
        }
    }

    override fun getYear(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat(String.YEAR_NUMBER_PATTERN, locale)
                    simpleDateFormat.format(toDate(it))
                } else String.EMPTY
            } catch (e: Exception) {
                Timber.e(e)
                String.EMPTY
            }
        } ?: String.EMPTY

    override fun isMonthOf(dateToCompare: String?, year: String?) =
        dateToCompare?.let {
            try {
                if (it.isNotEmpty()) it.contains(Regex(year.orEmpty())) else false
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    override fun isDayOf(dateToCompare: String?, month: String?, year: String?) =
        dateToCompare?.let {
            try {
                if (it.isNotEmpty())
                    it.contains(month.orEmpty() + Char.BAR + year.orEmpty()) else false
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    override fun getMonth(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat(String.MONTH_NUMBER_PATTERN, locale)
                    simpleDateFormat.format(toDate(it))
                } else String.EMPTY
            } catch (e: Exception) {
                Timber.e(e)
                String.EMPTY
            }
        } ?: String.EMPTY

    override fun getDay(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat(String.DAY_NUMBER_PATTERN, locale)
                    simpleDateFormat.format(toDate(it))
                } else String.EMPTY
            } catch (e: Exception) {
                Timber.e(e)
                String.EMPTY
            }
        } ?: String.EMPTY


    override fun isNextYear(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val yearToCompare = getYear(it).toInt()
                    val currentYear =
                        getYear(formatLongToStringDate(calendarInstance.time.time)).toInt()

                    yearToCompare == (currentYear + Int.ONE)
                } else {
                    false
                }
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    override fun isPastYear(stringDate: String?) =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) {
                    val yearToCompare = getYear(it).toInt()
                    val currentYear =
                        getYear(formatLongToStringDate(calendarInstance.time.time)).toInt()

                    yearToCompare == (currentYear - Int.ONE)
                } else {
                    false
                }
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } ?: false

    override fun isYesterday(stringDate: String?) =
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

    override fun isTomorrow(stringDate: String?) =
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

    override fun isToday(stringDate: String?) =
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

    override fun formatStringDateToLong(
        stringDate: String?,
        pattern: String
    ): Long =
        stringDate?.let {
            try {
                if (it.isNotEmpty()) SimpleDateFormat(
                    pattern,
                    locale
                ).parse(it)?.time else Long.ZERO
            } catch (e: ParseException) {
                Timber.e(e)
                Long.MIN_VALUE
            }
        } ?: Long.MIN_VALUE

    override fun formatLongToStringDate(
        dateInLong: Long?,
        pattern: String
    ) = dateInLong?.let {
        try {
            val date = Date(it)
            val formatter = SimpleDateFormat(pattern, locale).apply {
                timeZone = TimeZone.getTimeZone(Constants.UTC)
            }
            formatter.format(date) ?: String.EMPTY
        } catch (e: NumberFormatException) {
            Timber.e(e)
            String.EMPTY
        }
    } ?: String.EMPTY

}