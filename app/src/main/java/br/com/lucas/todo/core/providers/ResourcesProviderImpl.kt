package br.com.lucas.todo.core.providers

import android.content.res.Resources

class ResourcesProviderImpl(private val resources: Resources) : ResourcesProviderInterface {

    override fun getString(stringRes: Int): String =
        resources.getString(stringRes)

    override fun getString(stringRes: Int, vararg number: Number): String =
        resources.getString(stringRes, *number)


    override fun getString(stringRes: Int, vararg string: String): String =
        resources.getString(stringRes, *string)

}