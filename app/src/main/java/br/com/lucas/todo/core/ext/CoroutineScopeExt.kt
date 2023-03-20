package br.com.lucas.todo.core.ext

import timber.log.Timber

suspend fun <Call> safeCall(
    callToDo: suspend () -> Call?,
    onSuccess: suspend (Call) -> Unit = {},
    onError: suspend (e: Exception) -> Unit = {},
    onFinishCall: suspend () -> Unit = {}
) {
    try {
        callToDo()?.let { onSuccess(it) }
    } catch (e: Exception) {
        onError(e)
        Timber.e(e)
    }
    onFinishCall()
}