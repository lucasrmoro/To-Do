package br.com.lucas.todo.presentation.components

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import br.com.lucas.todo.MainActivity

class GenericToolbarMenu(private val activity: MainActivity) {

    private var menuProvider: MenuProvider? = null

    fun create(@MenuRes menuLayout: Int, onMenuItemClicked: (menuItem: MenuItem) -> Boolean) {
        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(menuLayout, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return onMenuItemClicked(menuItem)
            }

        }
        menuProvider?.let { (activity as MenuHost).addMenuProvider(it) }
    }

    fun showMenuItem(@IdRes menuItemId: Int) {
        activity.showMenuItem(menuItemId)
    }

    fun hideMenuItem(@IdRes menuItemId: Int) {
        activity.hideMenuItem(menuItemId)
    }

    fun destroy() {
        menuProvider?.let { (activity as MenuHost).removeMenuProvider(it) }
    }

}