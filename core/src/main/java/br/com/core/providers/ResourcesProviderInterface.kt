package br.com.core.providers

import androidx.annotation.StringRes

interface ResourcesProviderInterface {

    fun getString(@StringRes stringRes: Int): String

    fun getString(@StringRes stringRes: Int, vararg number: Number): String

    fun getString(@StringRes stringRes: Int, vararg string: String): String

}
