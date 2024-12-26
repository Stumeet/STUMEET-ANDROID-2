package com.aramtory.stumeet.coreui.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.component.data.StumeetRadioButtonSettingDto
import com.aramtory.stumeet.coreui.component.holder.StumeetRadioGroup84ViewHolder
import com.aramtory.stumeet.databinding.ComponentRadioButtonItem84Binding

class StumeetRadioGroup84Adapter(private val isSelectable: Boolean) :
    ListAdapter<StumeetRadioButtonSettingDto, StumeetRadioGroup84ViewHolder>(
        StumeetRadioGroup168DiffCallback()
    ) {

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StumeetRadioGroup84ViewHolder {
        val binding = ComponentRadioButtonItem84Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StumeetRadioGroup84ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StumeetRadioGroup84ViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (itemClick != null && isSelectable) {
            holder.itemBinding.tvBasicRadioButton.setOnClickListener { view ->
                itemClick?.onClick(view, position)
            }
        }
    }
}

class StumeetRadioGroup84DiffCallback : DiffUtil.ItemCallback<StumeetRadioButtonSettingDto>() {
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