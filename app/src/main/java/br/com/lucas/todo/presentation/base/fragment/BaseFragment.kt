package br.com.lucas.todo.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
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

    protected fun createToolbarMenu(
        @MenuRes menuLayout: Int,
        onMenuItemClicked: (menuItem: MenuItem) -> Unit
    ) {
        toolbarMenu = getMainActivity()?.let { GenericToolbarMenu(it) }
        toolbarMenu?.create(menuLayout) {
            onMenuItemClicked(it)
            true
        }
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
    override fun onPause() {
        super.onPause()
        toolbarMenu?.destroy()
        toolbarMenu = null
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}