package br.com.lucas.todo.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM: ViewModel> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private var _viewModel: Lazy<VM>? = null
    protected val viewModel: VM get() = _viewModel!!.value

    abstract fun setupViewBinding(layoutInflater: LayoutInflater): VB

    abstract fun setupViewModel(): Lazy<VM>

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