package br.com.lucas.todo.core.ext

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.errorState(isErrorState: Boolean, @StringRes errorText: Int) = with(context) {
    error = if (isErrorState) context.getString(errorText) else null
    editText?.setTextColor(if (isErrorState) getErrorColor() else getDefaultTextColor())
}