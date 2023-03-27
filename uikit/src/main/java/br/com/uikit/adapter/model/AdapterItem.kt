package br.com.uikit.adapter.model

interface AdapterItem : DiffUtilEquality {

    fun itemViewType(): Int

}