package br.com.core.extensions

import android.content.Context
import android.content.Intent

fun Context.intentByClassName(className: String) = Intent().setClassName(this, className)