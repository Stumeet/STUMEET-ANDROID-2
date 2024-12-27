package com.aramtory.stumeet.coreui.component.radio_button.data

data class StumeetRadioButtonSettingDto(
    val id: Int,
    val text: String,
    var backgroundTintResId: Int? = null,
    var textColorResId: Int? = null,
    var drawableEndResId: Int? = null,
    var isSelected: Boolean = false
)
