package com.aramtory.stumeet.presentation.study.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aramtory.stumeet.coreui.adapter.ItemClick
import com.aramtory.stumeet.data.dto.res.study.StudyListResDto
import com.aramtory.stumeet.databinding.ItemStudyBinding
import com.aramtory.stumeet.presentation.study.holder.StudyBasicViewHolder

class StudyBasicListAdapter() :
    ListAdapter<StudyListResDto.StudySimpleResponse, StudyBasicViewHolder>(
        StudyBasicListDiffCallback()
    ) {

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudyBasicViewHolder {
        val binding = ItemStudyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudyBasicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudyBasicViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (itemClick != null) {
            holder.itemBinding.clItemStudy.setOnClickListener { view ->
                itemClick?.onClick(view, position)
            }
        }
    }

}

class StudyBasicListDiffCallback : DiffUtil.ItemCallback<StudyListResDto.StudySimpleResponse>() {
    override fun areItemsTheSame(
        oldItem: StudyListResDto.StudySimpleResponse,
        newItem: StudyListResDto.StudySimpleResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: StudyListResDto.StudySimpleResponse,
        newItem: StudyListResDto.StudySimpleResponse
    ): Boolean {
        return oldItem == newItem
    }
}