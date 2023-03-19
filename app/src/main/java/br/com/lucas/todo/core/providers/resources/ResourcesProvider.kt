package br.com.lucas.todo.core.providers.resources

import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes stringRes: Int): String

    fun getString(@StringRes stringRes: Int, vararg number: Number): String

    fun getString(@StringRes stringRes: Int, vararg string: String): String

}
