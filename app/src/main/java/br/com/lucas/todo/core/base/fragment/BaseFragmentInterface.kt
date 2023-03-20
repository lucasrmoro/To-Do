package br.com.lucas.todo.core.base.fragment

import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

interface BaseFragmentInterface {

    fun showToast(@StringRes message: Int, duration: Int = Snackbar.LENGTH_LONG)

}