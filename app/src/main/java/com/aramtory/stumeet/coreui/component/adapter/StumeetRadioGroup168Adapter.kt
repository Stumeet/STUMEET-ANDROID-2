package com.aramtory.stumeet.coreui.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.component.data.StumeetRadioButtonSettingDto
import com.aramtory.stumeet.coreui.component.holder.StumeetRadioGroup168ViewHolder
import com.aramtory.stumeet.databinding.ComponentRadioButtonItem168Binding

class StumeetRadioGroup168Adapter(private val isSelectable: Boolean) :
    ListAdapter<StumeetRadioButtonSettingDto, StumeetRadioGroup168ViewHolder>(
        StumeetRadioGroup168DiffCallback()
    ) {

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StumeetRadioGroup168ViewHolder {
        val binding = ComponentRadioButtonItem168Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StumeetRadioGroup168ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StumeetRadioGroup168ViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (itemClick != null && isSelectable) {
            holder.itemBinding.tvBasicRadioButton.setOnClickListener { view ->
                itemClick?.onClick(view, position)
            }
        }
    }
}


class StumeetRadioGroup168DiffCallback : DiffUtil.ItemCallback<StumeetRadioButtonSettingDto>() {
    override fun areItemsTheSame(
        oldItem: StumeetRadioButtonSettingDto,
        newItem: StumeetRadioButtonSettingDto
    ): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(
        oldItem: StumeetRadioButtonSettingDto,
        newItem: StumeetRadioButtonSettingDto
    ): Boolean {
        return oldItem == newItem
    }
}
