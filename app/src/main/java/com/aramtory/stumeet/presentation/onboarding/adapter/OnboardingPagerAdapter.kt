package com.aramtory.stumeet.presentation.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aramtory.stumeet.databinding.ItemOnboardingBinding
import com.aramtory.stumeet.presentation.onboarding.data.OnboardingPagerDto
import com.aramtory.stumeet.presentation.onboarding.viewholder.OnboardingPagerViewHolder

class OnboardingPagerAdapter(private val items: List<OnboardingPagerDto>) :
    RecyclerView.Adapter<OnboardingPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingPagerViewHolder {
        val binding = ItemOnboardingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnboardingPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingPagerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
