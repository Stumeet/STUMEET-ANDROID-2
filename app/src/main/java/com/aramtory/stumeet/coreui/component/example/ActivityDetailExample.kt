package com.aramtory.stumeet.coreui.component.example

import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.component.activity_list.fragment.ActivityDetailListFragment
import com.aramtory.stumeet.databinding.ExampleFragmentBinding

class ActivityDetailExample :
    com.aramtory.stumeet.coreui.base.BindingFragment<ExampleFragmentBinding>(R.layout.example_fragment) {
    private lateinit var activityDetailListFragment: ActivityDetailListFragment

    override fun initView() {
        setupRecyclerView()
        setupData()
    }

    private fun setupRecyclerView() {
        activityDetailListFragment = ActivityDetailListFragment(
            onClick = { }
        )

        childFragmentManager.beginTransaction()
            .replace(R.id.layout_example_activity_detail_list, activityDetailListFragment)
            .commit()

        childFragmentManager.executePendingTransactions()
    }

    private fun setupData() {
        /**
         * 데이터 업데이트 필수
        activityDetailListFragment.submitList(filteredItems)
         */
    }
}