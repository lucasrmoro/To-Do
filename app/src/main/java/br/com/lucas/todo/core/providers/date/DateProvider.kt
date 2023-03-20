package br.com.lucas.todo.core.providers.date

import br.com.lucas.todo.core.Constants
import br.com.lucas.todo.core.ext.DEFAULT_DATE_PATTERN
import java.util.Date

interface DateProvider {

    fun toDate(stringDate: String, datePattern: String = String.DEFAULT_DATE_PATTERN): Date
    fun getStringDateBy(day: String?, month: String?, year: String?): String
    fun getYearsFrom(listOfDateString: List<String>?): List<String>
    fun getMonthsFrom(listOfDateString: List<String>?, desiredYear: String?): List<String>
    fun getDaysFrom(
        listOfDateString: List<String>?,
        desiredMonth: String?,
        desiredYear: String?
    ): List<String>
    fun getMonthNameFrom(monthNumber: String): String
    fun getFormattedYear(date: String?): String
    fun getFormattedMonthFrom(month: String?, year: String?): String
    fun isPastMonth(month: String?, year: String?): Boolean
    fun isNextMonth(month: String?, year: String?): Boolean
    fun isCurrentYear(year: String?): Boolean
    fun isCurrentMonth(month: String?, year: String?): Boolean
    fun getFormattedDay(
        day: String?,
        month: String?,
        year: String?,
        concatDayString: Boolean = true
    ): String
    fun isMonthOf(dateToCompare: String?, year: String?): Boolean
    fun isDayOf(dateToCompare: String?, month: String?, year: String?): Boolean
    fun isYesterday(stringDate: String?): Boolean
    fun isToday(stringDate: String?): Boolean
    fun isTomorrow(stringDate: String?): Boolean
    fun isNextYear(stringDate: String?): Boolean
    fun isPastYear(stringDate: String?): Boolean
    fun getDay(stringDate: String?): String
    fun getMonth(stringDate: String?): String
    fun getYear(stringDate: String?): String
    fun formatStringDateToLong(
        stringDate: String?,
        pattern: String = Constants.DEFAULT_DATE_PATTERN
    ): Long
    fun formatLongToStringDate(
        dateInLong: Long?,
        pattern: String = Constants.DEFAULT_DATE_PATTERN
    ): String
}