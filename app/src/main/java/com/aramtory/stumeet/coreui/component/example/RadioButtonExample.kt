package com.aramtory.stumeet.coreui.component.example

import android.view.View
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.component.radio_button.adapter.StumeetRadioGroup124Adapter
import com.aramtory.stumeet.coreui.component.radio_button.data.StumeetRadioButtonSettingDto
import com.aramtory.stumeet.databinding.ExampleFragmentBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class RadioButtonExample :
    com.aramtory.stumeet.coreui.base.BindingFragment<ExampleFragmentBinding>(R.layout.example_fragment) {
    private lateinit var adapter: StumeetRadioGroup124Adapter

    override fun initView() {
        setupRecyclerView()
        setupData()
    }

    private fun setupRecyclerView() {
        adapter = StumeetRadioGroup124Adapter(isSelectable = true).apply {
            // 아이템 클릭 설정
            itemClick = object : ItemClick {
                override fun onClick(view: View, position: Int) {
                    // 선택 시 설정
                    val updatedList = currentList.mapIndexed { index, item ->
                        item.copy(isSelected = index == position)
                    }
                    adapter.submitList(updatedList)
                }
            }
        }

        // layoutManager, adapter 설정
        binding.rcvExampleRadio.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.FLEX_START
            }
            adapter = this@RadioButtonExample.adapter
        }
    }

    private fun setupData() {
        val itemList = listOf(
            StumeetRadioButtonSettingDto(
                id = 1,
                text = "Option 1",
                isSelected = true,
            ),
            StumeetRadioButtonSettingDto(
                id = 2,
                text = "Option 2",
                isSelected = false,
            ),
            StumeetRadioButtonSettingDto(
                id = 3,
                text = "Option 3",
                isSelected = false,
            ),
            StumeetRadioButtonSettingDto(
                id = 1,
                text = "Option 1",
                isSelected = true,
            ),
            StumeetRadioButtonSettingDto(
                id = 2,
                text = "Option 2",
                isSelected = false,
            ),
            StumeetRadioButtonSettingDto(
                id = 3,
                text = "Option 3",
                isSelected = false,
            ),
        )

        // 어댑터에 데이터 전달
        adapter.submitList(itemList)
    }
}