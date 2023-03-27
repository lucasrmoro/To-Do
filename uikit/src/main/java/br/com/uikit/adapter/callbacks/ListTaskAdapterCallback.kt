package br.com.uikit.adapter.callbacks

import br.com.uikit.adapter.GenericAdapterCallback
import br.com.uikit.adapter.model.Task


interface ListTaskAdapterCallback: GenericAdapterCallback {

    fun onTaskClicked(task: Task)
    fun syncSelection(isSelected: Boolean, task: Task)

}