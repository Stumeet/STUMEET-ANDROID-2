package com.aramtory.stumeet.presentation.auth.signup

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.base.BindingFragment
import com.aramtory.stumeet.data.SharedManager
import com.aramtory.stumeet.databinding.FragmentSignUpSuccessBinding

class SignUpSuccessFragment :
    BindingFragment<FragmentSignUpSuccessBinding>(R.layout.fragment_sign_up_success) {

    override fun initView() {
        setButtonText()
        setStudyTextColor()
        initNextBtnClickListener()
    }

    private fun setButtonText() {
        binding.layoutSignupSuccessBtn.buttonText = getString(R.string.btn_next)
    }

    private fun setStudyTextColor() {
        val spannable = SpannableStringBuilder(getText(R.string.tv_signup_success))
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.primary_700)),
            0, // start
            5, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding.tvSignupSuccess.text = spannable
    }

    private fun initNextBtnClickListener() {
        binding.layoutSignupSuccessBtn.tvBasicButton.setOnClickListener {
            SharedManager.saveIsLogin(true)
            navigateToHomeFragment()
        }
    }

    private fun navigateToHomeFragment() =
        findNavController().navigate(R.id.navigation_home)
}