package br.com.lucas.todo.presentation.base.fragment

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
import androidx.viewbinding.ViewBinding
import br.com.lucas.todo.core.ext.getMainActivity
import br.com.lucas.todo.presentation.components.GenericToolbarMenu

abstract class BaseFragment<VB : ViewBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment(), BaseFragmentInterface {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

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
        destroyToolbarMenu()
        super.onDestroyView()
    }

}