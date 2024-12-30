package com.aramtory.stumeet.coreui.component.activity_list.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.component.activity_list.adapter.ActivityBriefAdapter
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityBriefListResDto
import com.aramtory.stumeet.databinding.FragmentActivityBriefListBinding

class ActivityBriefListFragment(
    private val onClick: () -> Unit,
) : com.aramtory.stumeet.coreui.base.BindingFragment<FragmentActivityBriefListBinding>(R.layout.fragment_activity_brief_list) {
    private lateinit var adapter: ActivityBriefAdapter

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ActivityBriefAdapter().apply {
            itemClick = object : ItemClick {
                override fun onClick(view: View, position: Int) {
                    onClick()
                }
            }
        }

        // layoutManager, adapter 설정
        binding.rcvActivityBriefList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ActivityBriefListFragment.adapter
        }
    }

    fun submitList(listData: List<StudyActivityBriefListResDto.Item>) {
        adapter.submitList(listData)
    }

    fun setVisibility(isListDataExist: Boolean) {
        if (isListDataExist) {
            binding.ivActivityBriefEmptyView.visibility = View.GONE
            binding.rcvActivityBriefList.visibility = View.VISIBLE
        } else {
            binding.ivActivityBriefEmptyView.visibility = View.VISIBLE
            binding.rcvActivityBriefList.visibility = View.GONE
        }
    }

    fun setEmptyViewDrawable(drawableResId : Int){
        binding.ivActivityBriefEmptyView.background = context?.getDrawable(drawableResId)
    }
}