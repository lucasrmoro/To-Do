package br.com.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment(), BaseFragmentInterface {

    private var _binding: VB? = null
    protected val binding
        get() = _binding ?: throw IllegalArgumentException("ViewBiding not found")

    protected val viewModel: VM by viewModel(clazz = getViewModelClass())

    @CallSuper
    override fun showToast(@StringRes message: Int, duration: Int) {
        context?.let {
            Toast.makeText(it, getString(message), duration).show()
        }
    }

    protected fun createToolbarMenu(
        @MenuRes menuLayout: Int,
        onMenuItemClicked: (menuItem: MenuItem) -> Unit
    ) {

    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass() = (javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments
        .firstOrNull { it is Class<*> && ViewModel::class.java.isAssignableFrom(it) }
        ?.let { it as Class<VM> }?.kotlin
        ?: throw IllegalStateException("ViewModel class not found")

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}