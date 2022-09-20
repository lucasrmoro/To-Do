package br.com.lucas.todo.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.hideAppBar() {
    (activity as? AppCompatActivity)?.supportActionBar?.run {
        if(isShowing) hide()
    }
}

fun Fragment.showAppBar() {
    (activity as? AppCompatActivity)?.supportActionBar?.run {
        if(!isShowing) show()
    }
}