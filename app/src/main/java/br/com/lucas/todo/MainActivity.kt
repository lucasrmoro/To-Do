package br.com.lucas.todo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.lucas.todo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
    }

    fun hideToolbar() { supportActionBar?.hide() }

    fun showToolbar() { supportActionBar?.show() }

    private fun setupToolbar() {
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).run {
            findNavController().addOnDestinationChangedListener { _, destination, _ ->
                supportActionBar?.setDisplayHomeAsUpEnabled(destination.id != R.id.listTaskFragment)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            findNavController(R.id.fragment_container).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}