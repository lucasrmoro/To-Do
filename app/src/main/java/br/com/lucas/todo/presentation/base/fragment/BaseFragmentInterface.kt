package br.com.lucas.todo.presentation.base.fragment

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

interface BaseFragmentInterface<VB: ViewBinding, VM: ViewModel> {

    fun setupViewBinding(layoutInflater: LayoutInflater): VB
    fun setupViewModel(): Lazy<VM>
    fun showToast(@StringRes message: Int, duration: Int = Snackbar.LENGTH_LONG)

}