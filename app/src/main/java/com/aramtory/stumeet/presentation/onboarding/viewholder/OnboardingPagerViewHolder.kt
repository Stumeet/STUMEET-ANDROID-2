package com.aramtory.stumeet.presentation.onboarding.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.aramtory.stumeet.databinding.ItemOnboardingBinding
import com.aramtory.stumeet.presentation.onboarding.data.OnboardingPagerDto

class OnboardingPagerViewHolder(private val binding: ItemOnboardingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: OnboardingPagerDto) {
        binding.ivOnboardingImage.setBackgroundResource(item.imageResId)
        binding.tvOnboardingText.setText(item.commentResId)
    }
}