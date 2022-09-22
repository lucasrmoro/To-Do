package br.com.lucas.todo.core.ext

import android.view.View
import android.widget.PopupMenu
import br.com.lucas.todo.R

fun View.showPopUp(onMenuClickListener: (itemId : Int) -> Unit) = with(PopupMenu(context, this)) {
    menuInflater.inflate(R.menu.popup_menu, menu)
    setOnMenuItemClickListener {
        onMenuClickListener(it.itemId)
        true
    }
    show()
}