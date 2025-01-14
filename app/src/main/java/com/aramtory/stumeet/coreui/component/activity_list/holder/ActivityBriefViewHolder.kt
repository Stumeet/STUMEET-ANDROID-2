package com.aramtory.stumeet.coreui.component.activity_list.holder

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.view.calculateDurationBetweenCurrentTime
import com.aramtory.stumeet.coreui.view.toCustomDateFormat
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityBriefListResDto
import com.aramtory.stumeet.data.enums.StudyActivityType
import com.aramtory.stumeet.databinding.ItemActivityBriefBinding
import java.util.Calendar

class ActivityBriefViewHolder(
    private val context: Context, private val binding: ItemActivityBriefBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val handler = Handler(Looper.getMainLooper())
    private var updateRunnable: Runnable? = null

    private val _itemBinding: ItemActivityBriefBinding = binding
    val itemBinding: ItemActivityBriefBinding = _itemBinding

    fun bind(data: StudyActivityBriefListResDto.Item) {
        with(binding) {
            // 기본 매핑
            tvActivityStudy.text = data.status // TODO 스터디명 주시면 바꾸기
            tvActivityBriefTitle.text = data.title
            tvActivityBriefType.text = StudyActivityType.getStudyActivity(context, data.category)
                ?.let { context.getString(it.getStudyActivityResId()) }

            // 카테고리에 따른 정보 설정
            if (data.category == StudyActivityType.ASSIGNMENT.name) {
                tvActivityBriefLocation.visibility = View.GONE
                tvActivityBriefTime.text = data.endDate?.toCustomDateFormat()
            } else {
                tvActivityBriefLocation.text = data.location
                tvActivityBriefTime.text = data.startDate?.toCustomDateFormat()
            }

            // 남은 시간 계산
            updateRemainTime(data)

            // 타이머 시작
            startTimer(data)
        }
    }

    private fun updateRemainTime(data: StudyActivityBriefListResDto.Item) {
        // 카테고리에 따른 시간 계산
        with(binding) {
            tvActivityBriefRemain.text = StudyActivityType.doWhenStudyActivityType(
                category = data.category,
                whenAssignment = {
                    data.endDate?.calculateDurationBetweenCurrentTime(context)
                },
                whenMeet = {
                    data.startDate?.calculateDurationBetweenCurrentTime(context)
                },
                whenDefault = {
                    tvActivityBriefRemain.isEnabled = false
                    null
                }
            ) ?: context.getString(R.string.tv_activity_brief_done)
        }
    }

    private fun startTimer(data: StudyActivityBriefListResDto.Item) {
        stopTimer()

        // 현재 시간으로 다음 분의 시작 시점을 계산
        val now = Calendar.getInstance()
        val nextMinuteStart = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.MINUTE, 1)
        }

        // 처음은 현재 시간 기준 다음 1분을 타겟팅
        val initialDelay = nextMinuteStart.timeInMillis - now.timeInMillis

        updateRunnable = object : Runnable {
            override fun run() {
                updateRemainTime(data)
                handler.postDelayed(this, 60000) // 이후에는 매 1분마다 실행
            }
        }

        // 초기 지연 시간 후 시작
        handler.postDelayed(updateRunnable!!, initialDelay)
    }

    fun stopTimer() {
        updateRunnable?.let {
            handler.removeCallbacks(it)
        }
        updateRunnable = null
    }
}