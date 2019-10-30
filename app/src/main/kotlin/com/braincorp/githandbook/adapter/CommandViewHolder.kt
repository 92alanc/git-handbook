package com.braincorp.githandbook.adapter

import android.view.View
import android.widget.TextView
import com.braincorp.githandbook.R
import com.braincorp.githandbook.model.CommandHeader

class CommandViewHolder(itemView: View) : ViewHolder<CommandHeader>(itemView) {

    private val txtName by bindView<TextView>(R.id.txt_name)
    private val txtParams by bindView<TextView>(R.id.txt_params)

    override fun bindTo(item: CommandHeader) {
        txtName.text = item.name
        txtParams.text = context.resources.getQuantityString(
                R.plurals.params_plural,
                item.paramsCount,
                item.paramsCount
        )
    }

}