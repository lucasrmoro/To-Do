package br.com.uikit.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <Call> ViewModel.viewModelCall(
    callToDo: suspend () -> Call?,
    onSuccess: suspend (Call) -> Unit = {},
    onError: (e: Exception) -> Unit = {},
    onFinishCall: () -> Unit = {}
) {
    viewModelScope.launch {
        try {
            callToDo()?.let { onSuccess(it) }
        } catch (e: Exception) {
            onError(e)
            e.printStackTrace()
        }
        onFinishCall()
    }
}