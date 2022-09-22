package br.com.lucas.todo.core.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.runOnViewModelScope(code: suspend () -> Unit) {
    viewModelScope.launch { code() }
}