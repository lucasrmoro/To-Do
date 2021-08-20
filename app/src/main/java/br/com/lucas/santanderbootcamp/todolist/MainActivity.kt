package br.com.lucas.santanderbootcamp.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}