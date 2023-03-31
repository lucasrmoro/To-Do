package br.com.lucas.todo.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val listOfMenuItemsToHide = mutableListOf<Int>()
    private val listOfMenuItemsToShow = mutableListOf<Int>()

    var menu: Menu? = null
        private set

    fun hideToolbar() {
        supportActionBar?.hide()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun showToolbar() {
        supportActionBar?.show()
    }

    fun hideMenuItem(@IdRes menuItemId: Int) {
        if(listOfMenuItemsToShow.contains(menuItemId)) {
            listOfMenuItemsToShow.remove(menuItemId)
        }
        if (!listOfMenuItemsToHide.contains(menuItemId)) {
            listOfMenuItemsToHide.add(menuItemId)
            invalidateOptionsMenu()
        }    }

    fun showMenuItem(@IdRes menuItemId: Int) {
        if(listOfMenuItemsToHide.contains(menuItemId)) {
            listOfMenuItemsToHide.remove(menuItemId)
        }
        if (!listOfMenuItemsToShow.contains(menuItemId)) {
            listOfMenuItemsToShow.add(menuItemId)
            invalidateOptionsMenu()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController(R.id.fragment_container).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        this.menu?.forEach {
            if (listOfMenuItemsToShow.contains(it.itemId)) {
                it.isVisible = true
            }
            if (listOfMenuItemsToHide.contains(it.itemId)) {
                it.isVisible = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu ?: run { this.menu = menu }
        return super.onCreateOptionsMenu(menu)
    }
}