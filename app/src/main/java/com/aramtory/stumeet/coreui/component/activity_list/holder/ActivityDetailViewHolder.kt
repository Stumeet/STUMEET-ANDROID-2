package com.aramtory.stumeet.coreui.component.activity_list.holder

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.context.dpToPx
import com.aramtory.stumeet.coreui.view.calculateDurationBetweenCurrentTime
import com.aramtory.stumeet.coreui.view.toCustomDateFormat
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityDetailListResDto
import com.aramtory.stumeet.data.enums.StudyActivityType
import com.aramtory.stumeet.databinding.ItemActivityDetailBinding
import java.util.Calendar

class ActivityDetailViewHolder(
    private val context: Context, private val binding: ItemActivityDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val handler = Handler(Looper.getMainLooper())
    private var updateRunnable: Runnable? = null

    private val _itemBinding: ItemActivityDetailBinding = binding
    val itemBinding: ItemActivityDetailBinding = _itemBinding

    fun bind(data: StudyActivityDetailListResDto.Item) {
        with(binding) {
            // 기본 매핑
            tvActivityStudy.text = data.title // TODO 스터디명 주시면 바꾸기
            tvActivityDetailTitle.text = data.title
            tvActivityDetailContent.text = data.content
            tvActivityDetailType.text = StudyActivityType.getStudyActivity(context, data.category)
                ?.let { context.getString(it.getStudyActivityResId()) }

            // 사용자 정보 설정
            if (data.author != null) {
                ivActivityDetailProfile.load(data.author.profileImageUrl) {
                    size(context.dpToPx(24))
                    transformations(CircleCropTransformation())
                }
                tvActivityDetailName.text = data.author.name
                // TODO 사용자 페이지로 이동하기
            } else {
                tvActivityDetailName.text = context.getString(R.string.tv_user_withdraw)
            }

            StudyActivityType.doWhenStudyActivityType(
                category = data.category,
                whenAssignment = {
                    tvActivityDetailLocation.visibility = View.GONE
                    tvActivityDetailTime.text = data.endDate?.toCustomDateFormat()                },
                whenMeet = {
                    tvActivityDetailLocation.text = data.location
                    tvActivityDetailTime.text = data.startDate?.toCustomDateFormat()
                },
                whenDefault = {
                    tvActivityDetailLocation.visibility = View.GONE
                    tvActivityDetailTime.visibility = View.GONE
                }
            )

            // 지난 시간 계산 및 초기 설정
            updateBeforeTime(data)

            // 타이머 시작
            startTimer(data)
        }
    }

    private fun updateBeforeTime(data: StudyActivityDetailListResDto.Item) {
        val beforeTime = data.createdAt.calculateDurationBetweenCurrentTime(
            context,
            isRemainCalculate = false
        )

        with(binding) {
            if (beforeTime != null) {
                tvActivityDetailBefore.text = beforeTime
            } else {
                tvActivityDetailBefore.text = context.getString(R.string.tv_activity_detail_done)
            }
        }
    }

    private fun startTimer(data: StudyActivityDetailListResDto.Item) {
        stopTimer()

        // 현재 시간으로 다음 분의 시작 시점을 계산
        val now = Calendar.getInstance()
        val nextMinuteStart = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.MINUTE, 1)
        }

        val initialDelay = nextMinuteStart.timeInMillis - now.timeInMillis

        updateRunnable = object : Runnable {
            override fun run() {
                updateBeforeTime(data)
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
