package br.com.core.extensions

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.startActivityByName(
    className: String,
    shouldFinishCallingActivity: Boolean = false,
    argsBuilder: Bundle.() -> Unit = {}
) {
    startActivity(intentByClassName(className).apply { putExtras(Bundle().apply(argsBuilder)) })
    if (shouldFinishCallingActivity) finish()
}