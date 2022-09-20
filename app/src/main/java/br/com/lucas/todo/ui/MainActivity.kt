package br.com.lucas.todo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAppBar()
    }

    private fun setupAppBar() = with(binding) {
        setSupportActionBar(toolbar)
        val navHost = supportFragmentManager.findFragmentById(fragmentContainer.id) as NavHostFragment
        navHost.findNavController().run {
            toolbar.setupWithNavController(this, AppBarConfiguration(graph))
        }
    }
}