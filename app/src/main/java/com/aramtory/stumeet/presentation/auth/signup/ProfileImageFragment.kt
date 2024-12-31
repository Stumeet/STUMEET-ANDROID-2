package com.aramtory.stumeet.presentation.auth.signup

import androidx.navigation.fragment.findNavController
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.base.BindingFragment
import com.aramtory.stumeet.databinding.FragmentProfileImageBinding

class ProfileImageFragment :
    BindingFragment<FragmentProfileImageBinding>(R.layout.fragment_profile_image) {

    override fun initView() {
        setButtonText()
        initNextBtnClickListener()
    }

    private fun setButtonText() {
        binding.layoutProfileBtn.buttonText = getString(R.string.btn_next)
    }

    private fun initNextBtnClickListener() {
        binding.layoutProfileBtn.tvBasicButton.setOnClickListener {
            findNavController().navigate(R.id.action_profile_image_to_nickname)
        }
    }
}