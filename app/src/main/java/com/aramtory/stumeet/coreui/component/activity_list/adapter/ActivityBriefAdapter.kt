package com.aramtory.stumeet.coreui.component.activity_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityBriefListResDto
import com.aramtory.stumeet.databinding.ItemActivityBriefBinding
import com.aramtory.stumeet.coreui.component.activity_list.holder.ActivityBriefViewHolder

class ActivityBriefAdapter() :
    ListAdapter<StudyActivityBriefListResDto.Item, ActivityBriefViewHolder>(
        ActivityBriefDiffCallback()
    ) {

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityBriefViewHolder {
        val binding = ItemActivityBriefBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ActivityBriefViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ActivityBriefViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (itemClick != null) {
            holder.itemBinding.clItemActivityBrief.setOnClickListener { view ->
                itemClick?.onClick(view, position)
            }
        }
    }

    override fun onViewRecycled(holder: ActivityBriefViewHolder) {
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

class ActivityBriefDiffCallback : DiffUtil.ItemCallback<StudyActivityBriefListResDto.Item>() {
    override fun areItemsTheSame(
        oldItem: StudyActivityBriefListResDto.Item,
        newItem: StudyActivityBriefListResDto.Item
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: StudyActivityBriefListResDto.Item,
        newItem: StudyActivityBriefListResDto.Item
    ): Boolean {
        return oldItem == newItem
    }
}