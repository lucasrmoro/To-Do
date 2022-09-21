package br.com.lucas.todo.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> T
): AppCompatActivity() {

    protected val binding by lazy { bindingInflater.invoke(layoutInflater) }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
    }
}