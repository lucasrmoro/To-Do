package br.com.lucas.todo.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment(),
    BaseFragmentInterface<VB, VM> {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private var _viewModel: Lazy<VM>? = null
    protected val viewModel: VM get() = _viewModel!!.value

    @CallSuper
    override fun showToast(@StringRes message: Int, duration: Int) {
        context?.let {
            Toast.makeText(it, getString(message), duration).show()
        }
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater)
        _viewModel = setupViewModel()
        return binding.root
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}