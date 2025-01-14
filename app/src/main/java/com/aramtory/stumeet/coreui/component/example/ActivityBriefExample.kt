package com.aramtory.stumeet.coreui.component.example

import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.component.activity_list.fragment.ActivityBriefListFragment
import com.aramtory.stumeet.databinding.ExampleFragmentBinding

class ActivityBriefExample :
    com.aramtory.stumeet.coreui.base.BindingFragment<ExampleFragmentBinding>(R.layout.example_fragment) {
    private lateinit var activityBriefListFragment: ActivityBriefListFragment

    override fun initView() {
        setupRecyclerView()
        setupData()
    }

    private fun setupRecyclerView() {
        activityBriefListFragment = ActivityBriefListFragment(
            onClick = { }
        )

        childFragmentManager.beginTransaction()
            .replace(R.id.layout_example_activity_brief_list, activityBriefListFragment)
            .commit()

        childFragmentManager.executePendingTransactions()
    }

    private fun setupData() {
        /**
         * 데이터 업데이트 필수
        activityBriefListFragment.submitList(filteredItems)
         */
    }

}