package com.aramtory.stumeet.coreui.component.activity_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.coreui.component.activity_list.holder.ActivityBriefViewHolder
import com.aramtory.stumeet.coreui.component.activity_list.holder.ActivityDetailViewHolder
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityDetailListResDto
import com.aramtory.stumeet.databinding.ItemActivityDetailBinding

class ActivityDetailAdapter() :
    ListAdapter<StudyActivityDetailListResDto.Item, ActivityDetailViewHolder>(
        ActivityDetailDiffCallback()
    ) {

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityDetailViewHolder {
        val binding = ItemActivityDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ActivityDetailViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ActivityDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (itemClick != null) {
            holder.itemBinding.clItemActivityDetail.setOnClickListener { view ->
                itemClick?.onClick(view, position)
            }
        }
    }

    override fun onViewRecycled(holder: ActivityDetailViewHolder) {
        holder.stopTimer()
        super.onViewRecycled(holder)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        for (i in 0 until itemCount) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i)
            if (viewHolder is ActivityBriefViewHolder) {
                viewHolder.stopTimer()
            }
        }
        super.onDetachedFromRecyclerView(recyclerView)
    }
}

class ActivityDetailDiffCallback : DiffUtil.ItemCallback<StudyActivityDetailListResDto.Item>() {
    override fun areItemsTheSame(
        oldItem: StudyActivityDetailListResDto.Item,
        newItem: StudyActivityDetailListResDto.Item
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: StudyActivityDetailListResDto.Item,
        newItem: StudyActivityDetailListResDto.Item
    ): Boolean {
        return oldItem == newItem
    }
}