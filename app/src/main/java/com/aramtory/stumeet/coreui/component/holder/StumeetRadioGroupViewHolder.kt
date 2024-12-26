package com.aramtory.stumeet.coreui.component.holder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aramtory.stumeet.coreui.component.data.StumeetRadioButtonSettingDto
import com.aramtory.stumeet.databinding.ComponentRadioButtonItemBinding

class StumeetRadioGroupViewHolder(private val binding: ComponentRadioButtonItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val _itemBinding: ComponentRadioButtonItemBinding = binding
    val itemBinding: ComponentRadioButtonItemBinding = _itemBinding

    fun bind(data: StumeetRadioButtonSettingDto) {
        // text 설정
        with(binding.tvBasicRadioButton) {
            // Background 설정
            if(data.backgroundTintResId != null){
                setBackgroundResource(data.backgroundTintResId!!)
            }

            // Text color 설정
            if(data.textColorResId != null){
                setTextColor(ContextCompat.getColorStateList(context, data.textColorResId!!) ?: return)
            }

            // Drawable 설정
            setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                data.drawableEndResId?.let { ContextCompat.getDrawable(context, it) },
                null
            )

            // text 설정
            text = data.text

            // 눌림 설정
            isSelected = data.isSelected
        }

    }
}