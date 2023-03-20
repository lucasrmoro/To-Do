package br.com.lucas.todo.core.ext

val String.Companion.EMPTY
    get() = ""

val String.Companion.SPACE
    get() = " "

val String.Companion.COLON
    get() = ":"

val String.Companion.LESS_ONE
    get() = "-1"

val String.Companion.ZERO
    get() = "0"

val String.Companion.ONE
    get() = "1"

val String.Companion.TWENTY_EIGHT
    get() = "28"

val String.Companion.TWENTY_NINE
    get() = "29"

val String.Companion.THIRTY
    get() = "30"

val String.Companion.THIRTY_ONE
    get() = "31"

val String.Companion.JANUARY_IN_PT_BR
    get() = "janeiro"

val String.Companion.FEBRUARY_IN_PT_BR
    get() = "fevereiro"

val String.Companion.MARCH_IN_PT_BR
    get() = "março"

val String.Companion.APRIL_IN_PT_BR
    get() = "abril"

val String.Companion.MAY_IN_PT_BR
    get() = "maio"

val String.Companion.JUNE_IN_PT_BR
    get() = "junho"

val String.Companion.JULY_IN_PT_BR
    get() = "julho"

val String.Companion.AUGUST_IN_PT_BR
    get() = "agosto"

val String.Companion.SEPTEMBER_IN_PT_BR
    get() = "setembro"

val String.Companion.OCTOBER_IN_PT_BR
    get() = "outubro"

val String.Companion.NOVEMBER_IN_PT_BR
    get() = "novembro"

val String.Companion.DECEMBER_IN_PT_BR
    get() = "dezembro"

fun String?.withNoSpace() = this?.filterNot { it == Char.SPACE } ?: String.EMPTY

//Patterns

val String.Companion.DEFAULT_DATE_PATTERN
    get() = "dd/MM/yyyy"

val String.Companion.DAY_NUMBER_PATTERN
    get() = "dd"

val String.Companion.MONTH_NUMBER_PATTERN
    get() = "MM"

val String.Companion.YEAR_NUMBER_PATTERN
    get() = "yyyy"