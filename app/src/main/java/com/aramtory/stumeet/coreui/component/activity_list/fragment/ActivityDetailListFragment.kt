package com.aramtory.stumeet.coreui.component.activity_list.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.base.BindingFragment
import com.aramtory.stumeet.coreui.component.activity_list.adapter.ActivityDetailAdapter
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityDetailListResDto
import com.aramtory.stumeet.databinding.FragmentActivityDetailListBinding

class ActivityDetailListFragment(
    private val onClick: () -> Unit,
) : BindingFragment<FragmentActivityDetailListBinding>(R.layout.fragment_activity_detail_list) {
    private lateinit var adapter: ActivityDetailAdapter

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ActivityDetailAdapter().apply {
            itemClick = object : ItemClick {
                override fun onClick(view: View, position: Int) {
                    onClick()
                }
            }
        }

        // layoutManager, adapter 설정
        binding.rcvActivityDetailList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ActivityDetailListFragment.adapter
        }
    }

    fun submitList(listData: List<StudyActivityDetailListResDto.Item>) {
        adapter.submitList(listData)
    }

    fun setVisibility(isListDataExist: Boolean) {
        if (isListDataExist) {
            binding.ivActivityDetailEmptyView.visibility = View.GONE
            binding.rcvActivityDetailList.visibility = View.VISIBLE
        } else {
            binding.ivActivityDetailEmptyView.visibility = View.VISIBLE
            binding.rcvActivityDetailList.visibility = View.GONE
        }
    }

    fun setEmptyViewDrawable(drawableResId : Int){
        binding.ivActivityDetailEmptyView.background = context?.getDrawable(drawableResId)
    }
}