package br.com.lucas.todo.presentation.components

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider

class GenericToolbarMenu(private val activity: AppCompatActivity) {

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

    fun destroy() {
        menuProvider?.let { (activity as MenuHost).removeMenuProvider(it) }
    }

}