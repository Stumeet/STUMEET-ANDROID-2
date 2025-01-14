package com.aramtory.stumeet.presentation.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aramtory.stumeet.coreui.component.activity_list.fragment.ActivityBriefListFragment
import com.aramtory.stumeet.coreui.component.activity_list.fragment.ActivityDetailListFragment

class HomePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val onClick: () -> Unit
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentMap = mutableMapOf<Int, Fragment>()

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> ActivityBriefListFragment(onClick = { onClick() })
            1 -> ActivityDetailListFragment(onClick = { onClick() })
            else -> throw IllegalStateException("Invalid position $position")
        }

        fragmentMap[position] = fragment
        return fragment
    }

    fun getFragment(position: Int): Fragment? {
        return fragmentMap[position]
    }
}
