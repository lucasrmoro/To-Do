package br.com.lucas.todo.core.extensions

import androidx.fragment.app.Fragment
import br.com.lucas.todo.ui.MainActivity

fun Fragment.getMainActivity() = (activity as? MainActivity)