package com.aramtory.stumeet.presentation.onboarding

import android.content.Intent
import android.content.SharedPreferences
import com.aramtory.stumeet.MainActivity
import com.aramtory.stumeet.R
import com.aramtory.stumeet.databinding.ActivityOnboardingBinding
import com.aramtory.stumeet.presentation.onboarding.adapter.OnboardingPagerAdapter
import com.aramtory.stumeet.presentation.onboarding.data.OnboardingPagerDto


class OnboardingActivity :
    com.aramtory.stumeet.coreui.base.BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private lateinit var sharedPreferences: SharedPreferences

    override fun initView() {
        // 첫 실행 여부 확인
        sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        if (!isFirstRun()) {
            goToMainActivity()
        }

        // 데이터 준비
        val onboardingItems = listOf(
            OnboardingPagerDto(
                R.drawable.img_onboarding_1,
                R.string.tv_onboarding_comment_1
            ),
            OnboardingPagerDto(
                R.drawable.img_onboarding_2,
                R.string.tv_onboarding_comment_2
            ),
            OnboardingPagerDto(
                R.drawable.img_onboarding_3,
                R.string.tv_onboarding_comment_3
            )
        )

        // Adapter 설정
        val onboardingPagerAdapter = OnboardingPagerAdapter(onboardingItems)
        binding.vpOnboardingViewpager.adapter = onboardingPagerAdapter

        // Indicator 설정
        binding.ciOnboardingIndicator.setViewPager(binding.vpOnboardingViewpager)

        // 다음 버튼 설정
        setButtonText()
        binding.layoutOnboardingBtn.tvBasicButton.setOnClickListener {
            val currentItem = binding.vpOnboardingViewpager.currentItem
            if (currentItem < onboardingItems.size - 1) {
                binding.vpOnboardingViewpager.currentItem = currentItem + 1
            } else {
                markOnboardingCompleted()
                goToMainActivity()
            }
        }
    }

    private fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean("first_run", true)
    }

    private fun markOnboardingCompleted() {
        sharedPreferences.edit().putBoolean("first_run", false).apply()
    }

    private fun setButtonText() {
        binding.layoutOnboardingBtn.buttonText = getString(R.string.btn_next)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}