package br.com.lucas.todo.presentation.common.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import br.com.lucas.todo.core.ext.getMainActivity
import br.com.lucas.todo.presentation.common.generic.components.GenericToolbarMenu
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment(), BaseFragmentInterface {

    private var _binding: VB? = null
    protected val binding
        get() = _binding ?: throw IllegalArgumentException("ViewBiding not found")

    private var _viewModel: VM? = null
    protected val viewModel: VM
        get() = _viewModel ?: throw IllegalArgumentException("ViewModel not found")

    private var toolbarMenu: GenericToolbarMenu? = null

    @CallSuper
    override fun showToast(@StringRes message: Int, duration: Int) {
        context?.let {
            Toast.makeText(it, getString(message), duration).show()
        }
    }

    protected fun showMenuItem(@IdRes menuItemId: Int) {
        toolbarMenu?.showMenuItem(menuItemId)
    }

    protected fun hideMenuItem(@IdRes menuItemId: Int) {
        toolbarMenu?.hideMenuItem(menuItemId)
    }

    protected fun createToolbarMenu(
        @MenuRes menuLayout: Int,
        onMenuItemClicked: (menuItem: MenuItem) -> Unit
    ) {
        toolbarMenu ?: let {
            toolbarMenu = getMainActivity()?.let { GenericToolbarMenu(it) }?.also {
                it.create(menuLayout) { menuItem ->
                    onMenuItemClicked(menuItem)
                    true
                }
            }
        }
    }

    private fun destroyToolbarMenu() {
        toolbarMenu?.destroy()
        toolbarMenu = null
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass() = (javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments
        .firstOrNull { it is Class<*> && ViewModel::class.java.isAssignableFrom(it) }
        ?.let { it as Class<VM> }
        ?: throw IllegalStateException("ViewModel class not found")

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        _viewModel = ViewModelProvider(this)[getViewModelClass()]
        return binding.root
    }

    @CallSuper
    override fun onDestroyView() {
        _binding = null
        destroyToolbarMenu()
        super.onDestroyView()
    }

}