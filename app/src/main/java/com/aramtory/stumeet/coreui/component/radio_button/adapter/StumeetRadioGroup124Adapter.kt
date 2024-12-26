
package com.aramtory.stumeet.coreui.component.radio_button.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.component.radio_button.data.StumeetRadioButtonSettingDto
import com.aramtory.stumeet.coreui.component.radio_button.holder.StumeetRadioGroup124ViewHolder
import com.aramtory.stumeet.databinding.ComponentRadioButtonItem124Binding

class StumeetRadioGroup124Adapter(private val isSelectable: Boolean) :
    ListAdapter<StumeetRadioButtonSettingDto, StumeetRadioGroup124ViewHolder>(
        StumeetRadioGroup124DiffCallback()
    ) {

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StumeetRadioGroup124ViewHolder {
        val binding = ComponentRadioButtonItem124Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StumeetRadioGroup124ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StumeetRadioGroup124ViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (itemClick != null && isSelectable) {
            holder.itemBinding.tvBasicRadioButton.setOnClickListener { view ->
                itemClick?.onClick(view, position)
            }
        }
    }
}


class StumeetRadioGroup124DiffCallback : DiffUtil.ItemCallback<StumeetRadioButtonSettingDto>() {
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