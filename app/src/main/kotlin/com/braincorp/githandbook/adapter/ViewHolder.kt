package com.braincorp.githandbook.adapter

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val context = itemView.context

    protected fun <V: View> bindView(@IdRes id: Int) = lazy { itemView.findViewById<V>(id) }

    abstract fun bindTo(item: T)

}