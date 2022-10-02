package br.com.lucas.todo.core.ext

import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.getStringText() = text?.trim().toString()

fun TextInputEditText.getHour() = text?.split(':')?.get(0) ?: ""

fun TextInputEditText.getMinute() = text?.split(':')?.get(1) ?: ""