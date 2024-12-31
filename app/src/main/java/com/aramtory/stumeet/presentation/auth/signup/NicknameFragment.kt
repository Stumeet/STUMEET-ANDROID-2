package com.aramtory.stumeet.presentation.auth.signup

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.base.BindingFragment
import com.aramtory.stumeet.databinding.FragmentNicknameBinding

class NicknameFragment :
    BindingFragment<FragmentNicknameBinding>(R.layout.fragment_nickname) {

    override fun initView() {
        setupTextWatcher()
        setButtonText()
        initNextBtnClickListener()
        navigateToBack()
    }

    private fun setupTextWatcher() {
        binding.tvNameInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.tvNameCounter.visibility = View.VISIBLE

                val textLength = s?.length ?: 0
                binding.tvNameCounter.text =
                    getString(R.string.tv_name_counter, textLength, MAX_NAME_LENGTH)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setButtonText() {
        binding.layoutNameBtn.buttonText = getString(R.string.btn_next)
    }

    private fun initNextBtnClickListener() {
        binding.layoutNameBtn.tvBasicButton.setOnClickListener {
            findNavController().navigate(R.id.action_nickname_to_region_selection)
        }
    }

    private fun navigateToBack() {
        binding.layoutAppBar.btnAppBarBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        private const val MAX_NAME_LENGTH = 10
    }
}