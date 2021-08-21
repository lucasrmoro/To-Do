package br.com.lucas.santanderbootcamp.todolist.core.extensions

import java.text.SimpleDateFormat
import java.util.*

private val locale = Locale("pt", "BR")

fun Date.formatDateToPtBr() : String = SimpleDateFormat("dd/MM/yyyy", locale).format(this)