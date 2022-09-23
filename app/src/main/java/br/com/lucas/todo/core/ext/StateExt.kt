package br.com.lucas.todo.core.ext

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<Boolean>.isTrue() = this.value == true