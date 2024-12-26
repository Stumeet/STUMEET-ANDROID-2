package com.aramtory.stumeet.coreui.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.component.data.StumeetRadioButtonSettingDto
import com.aramtory.stumeet.coreui.component.holder.StumeetRadioGroupViewHolder
import com.aramtory.stumeet.databinding.ComponentRadioButtonItemBinding

class StumeetRadioGroupAdapter(private val isSelectable : Boolean) :
    ListAdapter<StumeetRadioButtonSettingDto, StumeetRadioGroupViewHolder>(
        StumeetRadioGroupDiffCallback()
    ) {

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StumeetRadioGroupViewHolder {
        val binding = ComponentRadioButtonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StumeetRadioGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StumeetRadioGroupViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (itemClick != null && isSelectable) {
            holder.itemBinding.tvBasicRadioButton.setOnClickListener { view ->
                itemClick?.onClick(view, position)
            }
        }
    }
}


class StumeetRadioGroupDiffCallback : DiffUtil.ItemCallback<StumeetRadioButtonSettingDto>() {
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