package com.aramtory.stumeet.presentation.auth.signup

import android.view.View
import androidx.navigation.fragment.findNavController
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.base.BindingFragment
import com.aramtory.stumeet.coreui.component.radio_button.adapter.StumeetRadioGroup168Adapter
import com.aramtory.stumeet.coreui.component.radio_button.data.StumeetRadioButtonSettingDto
import com.aramtory.stumeet.databinding.FragmentRegionSelectionBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class RegionSelectionFragment :
    BindingFragment<FragmentRegionSelectionBinding>(R.layout.fragment_region_selection) {
    private lateinit var adapter: StumeetRadioGroup168Adapter

    override fun initView() {
        setButtonText()
        initNextBtnClickListener()
        navigateToBack()
        setupRecyclerView()
        setupData()
    }

    private fun setButtonText() {
        binding.layoutRegionBtn.buttonText = getString(R.string.btn_next)
    }

    private fun navigateToBack() {
        binding.layoutAppBar.btnAppBarBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initNextBtnClickListener() {
        binding.layoutRegionBtn.tvBasicButton.setOnClickListener {
            findNavController().navigate(R.id.action_region_selection_to_study_field)
        }
    }

    private fun setupRecyclerView() {
        adapter = StumeetRadioGroup168Adapter(isSelectable = true).apply {
            itemClick = object : ItemClick {
                override fun onClick(view: View, position: Int) {
                    val updatedList = currentList.mapIndexed { index, item ->
                        item.copy(isSelected = index == position)
                    }
                    submitList(updatedList)
                }
            }
        }

        binding.rvRegionRadio.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.FLEX_START
            }
            adapter = this@RegionSelectionFragment.adapter
        }
    }

    private fun setupData() {
        val itemList = listOf(
            StumeetRadioButtonSettingDto(id = 1, text = "서울", isSelected = false),
            StumeetRadioButtonSettingDto(id = 2, text = "인천/경기", isSelected = false),
            StumeetRadioButtonSettingDto(id = 3, text = "전북", isSelected = false),
            StumeetRadioButtonSettingDto(id = 4, text = "전남", isSelected = false),
            StumeetRadioButtonSettingDto(id = 5, text = "강원", isSelected = false),
            StumeetRadioButtonSettingDto(id = 6, text = "경북", isSelected = false),
            StumeetRadioButtonSettingDto(id = 7, text = "경남", isSelected = false),
            StumeetRadioButtonSettingDto(id = 8, text = "충북", isSelected = false),
            StumeetRadioButtonSettingDto(id = 9, text = "충남", isSelected = false),
            StumeetRadioButtonSettingDto(id = 10, text = "제주", isSelected = false)
        )
        adapter.submitList(itemList)
    }
}