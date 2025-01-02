package com.aramtory.stumeet.presentation.study.holder

import androidx.recyclerview.widget.RecyclerView
import com.aramtory.stumeet.coreui.view.toCustomDateFormat
import com.aramtory.stumeet.data.dto.res.study.StudyListResDto
import com.aramtory.stumeet.databinding.ItemStudyBinding
import java.time.format.DateTimeFormatter

class StudyBasicViewHolder(private val binding: ItemStudyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val _itemBinding: ItemStudyBinding = binding
    val itemBinding: ItemStudyBinding = _itemBinding

    fun bind(data: StudyListResDto.StudySimpleResponse) {
        with(binding) {
            tvStudyMember.text = data.headcount.toString()
            tvStudyName.text = data.name
            val startDate = data.startDate.toCustomDateFormat(
                outputFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            )
            val endDate = data.endDate.toCustomDateFormat(
                outputFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            )
            "$startDate ~ $endDate".also { tvStudyStartEndDate.text = it }
        }
    }
}