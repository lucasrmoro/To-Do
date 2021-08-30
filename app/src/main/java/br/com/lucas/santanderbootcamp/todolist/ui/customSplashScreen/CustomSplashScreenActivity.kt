package br.com.lucas.santanderbootcamp.todolist.ui.customSplashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivitySplashScreenBinding
import br.com.lucas.santanderbootcamp.todolist.ui.listTask.ListTaskActivity


class CustomSplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivCheck.alpha = 0f
        binding.ivCheck.animate().setDuration(3000).alpha(1f).withEndAction {
            startActivity(Intent(this@CustomSplashScreenActivity, ListTaskActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}