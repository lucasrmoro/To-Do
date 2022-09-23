package br.com.lucas.todo.core.ext

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <Call> ViewModel.viewModelCall(
    callToDo: suspend () -> Call,
    onSuccess: (Call?) -> Unit = {},
    onError: (e: Exception) -> Unit = {},
    onFinishCall: () -> Unit = {}
) {
    viewModelScope.launch {
        try {
            callToDo().let(onSuccess)
        } catch (e: Exception) {
            onError(e)
            Log.d("ViewModel Call Error", e.message.toString())
        }
        onFinishCall()
    }
}