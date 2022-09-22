package br.com.lucas.todo.core.ext

import androidx.fragment.app.Fragment
import br.com.lucas.todo.MainActivity

fun Fragment.getMainActivity() = (activity as? MainActivity)