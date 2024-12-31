package com.aramtory.stumeet.presentation.auth.signup

import android.view.View
import androidx.navigation.fragment.findNavController
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.base.BindingFragment
import com.aramtory.stumeet.coreui.component.radio_button.adapter.StumeetRadioGroup168Adapter
import com.aramtory.stumeet.coreui.component.radio_button.data.StumeetRadioButtonSettingDto
import com.aramtory.stumeet.databinding.FragmentStudyFieldBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class StudyFieldFragment :
    BindingFragment<FragmentStudyFieldBinding>(R.layout.fragment_study_field) {
    private lateinit var adapter: StumeetRadioGroup168Adapter

    override fun initView() {
        setButtonText()
        initNextBtnClickListener()
        navigateToBack()
        setupRecyclerView()
        setupData()
    }

    private fun setButtonText() {
        binding.layoutFieldBtn.buttonText = getString(R.string.btn_next)
    }

    private fun navigateToBack() {
        binding.layoutAppBar.btnAppBarBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initNextBtnClickListener() {
        binding.layoutFieldBtn.tvBasicButton.setOnClickListener {
            findNavController().navigate(R.id.action_study_field_to_signUp_success)
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

        binding.rvFieldRadio.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.FLEX_START
            }
            adapter = this@StudyFieldFragment.adapter
        }
    }

    private fun setupData() {
        val itemList = listOf(
            StumeetRadioButtonSettingDto(id = 1, text = "IT", isSelected = false),
            StumeetRadioButtonSettingDto(id = 2, text = "출판", isSelected = false),
            StumeetRadioButtonSettingDto(id = 3, text = "디자인", isSelected = false),
            StumeetRadioButtonSettingDto(id = 4, text = "마케팅/기획", isSelected = false),
            StumeetRadioButtonSettingDto(id = 5, text = "어학", isSelected = false),
            StumeetRadioButtonSettingDto(id = 6, text = "취업준비", isSelected = false),
            StumeetRadioButtonSettingDto(id = 7, text = "자연계", isSelected = false),
            StumeetRadioButtonSettingDto(id = 8, text = "방송", isSelected = false),
            StumeetRadioButtonSettingDto(id = 9, text = "자율스터디", isSelected = false),
            StumeetRadioButtonSettingDto(id = 10, text = "경제", isSelected = false),
            StumeetRadioButtonSettingDto(id = 11, text = "자격증", isSelected = false),
            StumeetRadioButtonSettingDto(id = 12, text = "인문계", isSelected = false),
            StumeetRadioButtonSettingDto(id = 13, text = "봉사활동", isSelected = false)
        )
        adapter.submitList(itemList)
    }
}