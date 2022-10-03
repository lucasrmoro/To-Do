package br.com.lucas.todo.core.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

fun <Call> ViewModel.viewModelCall(
    callToDo: suspend () -> Call?,
    onSuccess: (Call) -> Unit = {},
    onError: (e: Exception) -> Unit = {},
    onFinishCall: () -> Unit = {}
) {
    viewModelScope.launch {
        try {
            callToDo()?.let { onSuccess(it) }
        } catch (e: Exception) {
            onError(e)
            Timber.e(e)
        }
        onFinishCall()
    }
}