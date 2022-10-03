package br.com.lucas.todo.core.ext

import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.getStringText() = text?.trim().toString()

fun TextInputEditText.getHour() = try {
    text?.split(':')?.get(0) ?: ""
} catch (e: IndexOutOfBoundsException) {
    ""
}

fun TextInputEditText.getMinute() = try {
    text?.split(':')?.get(1) ?: ""
} catch (e: IndexOutOfBoundsException) {
    ""
}