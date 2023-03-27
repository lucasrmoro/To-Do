package br.com.uikit.extensions

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<Boolean>.isTrue() = this.value == true