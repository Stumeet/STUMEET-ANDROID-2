package com.aramtory.stumeet.presentation.calendar


import android.view.View
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.component.activity_list.fragment.ActivityBriefListFragment
import com.aramtory.stumeet.coreui.fragment.dpToPx
import com.aramtory.stumeet.coreui.view.toLocalDateTime
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityBriefListResDto
import com.aramtory.stumeet.data.enums.StudyActivityType
import com.aramtory.stumeet.databinding.FragmentCalendarBinding
import com.aramtory.stumeet.presentation.calendar.decorator.CurrentAndSelectedDayDecorator
import com.aramtory.stumeet.presentation.calendar.decorator.EventDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.time.LocalDate

class CalendarFragment :
    com.aramtory.stumeet.coreui.base.BindingFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {
    private lateinit var activityBriefListFragment: ActivityBriefListFragment

    // 할 일이 있는 날짜
    private lateinit var datesWithEvents: Set<CalendarDay>
    private val selected = CalendarDay.today()

    override fun initView() {

        val currentMonth = LocalDate.now().monthValue
        datesWithEvents = extractUniqueDates(exampleItems, currentMonth)

        setupRecyclerView()
        setCalendarUI()
    }

    private fun setupRecyclerView() {
        activityBriefListFragment = ActivityBriefListFragment(
            onClick = {
                // TODO 화면 이동 구현
            }
        )

        childFragmentManager.beginTransaction()
            .replace(R.id.layout_activity_brief_list, activityBriefListFragment)
            .commit()

        childFragmentManager.executePendingTransactions()
    }


    private fun setCalendarUI() {
        with(binding.cvCalendar) {
            // 기본 UI 초기화
            setLeftArrow(R.drawable.ic_empty_drawable)
            setRightArrow(R.drawable.ic_empty_drawable)
            setTitleFormatter { "" }
            setWeekDayTextAppearance(R.style.TextAppearance_Stumeet_CalendarWeek)
            setDateTextAppearance(R.style.TextAppearance_Stumeet_Body2)
            clipToPadding = false

            // 기본 시작일 초기화
            val currentLocalDate = LocalDate.now()
            val startDate = CalendarDay.from(currentLocalDate.year, currentLocalDate.monthValue, 1)
            currentDate = startDate
            updateYearMonthText()
            setupData(CalendarDay.today())

            // 기본 시작 decorator 추가
            addDecorators(
                // 선택 item
                CurrentAndSelectedDayDecorator(requireContext()),
                // 이벤트 item
                EventDecorator(datesWithEvents, dpToPx(8))
            )

            // 월이 바뀐 경우
            setOnMonthChangedListener { _, date ->
                updateYearMonthText()
                removeDecorators()
                datesWithEvents = extractUniqueDates(exampleItems, date.month)
                addDecorators(
                    CurrentAndSelectedDayDecorator(requireContext(), selected),
                    EventDecorator(datesWithEvents, dpToPx(8))
                )
            }

            // 캘린더 날짜 선택 시
            setOnDateChangedListener { _, date, _ ->
                selectedDate = date
                // 기존 decorator 제거
                removeDecorators()// 커스텀

                addDecorators(
                    // 선택 item
                    CurrentAndSelectedDayDecorator(requireContext(), date),
                    // 이벤트 item
                    EventDecorator(datesWithEvents, dpToPx(8))
                )

                // RecyclerView 데이터 업데이트
                setupData(date)
            }

            with(binding) {
                // 캘린더 좌우 버튼 클릭 시
                ivLeftArrow.setOnClickListener {
                    binding.cvCalendar.goToPrevious()
                }
                ivRightArrow.setOnClickListener {
                    binding.cvCalendar.goToNext()
                }
            }
        }
    }

    private fun setupData(selectedDate: CalendarDay?) {
        // 선택된 날짜에 해당하는 아이템 필터링
        val filteredItems = exampleItems.filter { item ->
            // category에 따라 조건 처리
            val itemDate = when (item.category) {
                StudyActivityType.MEET.name -> item.startDate?.toLocalDateTime()
                StudyActivityType.ASSIGNMENT.name -> item.endDate?.toLocalDateTime()
                else -> null // DEFAULT 및 기타 카테고리는 제외
            }

            // itemDate가 selectedDate와 동일한 날짜인지 확인
            itemDate?.let {
                it.year == selectedDate?.year &&
                        it.monthValue == selectedDate.month &&
                        it.dayOfMonth == selectedDate.day
            } ?: false
        }

        // 필터링 결과에 따라 UI 업데이트
        with(binding) {
            if (filteredItems.isEmpty()) {
                ivCalendarEmptyView.visibility = View.VISIBLE
                layoutActivityBriefList.visibility = View.GONE
            } else {
                ivCalendarEmptyView.visibility = View.GONE
                layoutActivityBriefList.visibility = View.VISIBLE
                layoutActivityBriefList.post {
                    activityBriefListFragment.submitList(filteredItems)
                }
            }

        }
    }

    private fun extractUniqueDates(
        items: List<StudyActivityBriefListResDto.Item>,
        currentMonth: Int
    ): Set<CalendarDay> {
        return items.mapNotNull { item ->
            val dateTime = when (item.category) {
                StudyActivityType.ASSIGNMENT.name -> item.endDate?.toLocalDateTime()
                StudyActivityType.MEET.name -> item.startDate?.toLocalDateTime()
                else -> null
            }

            dateTime?.let {
                if (it.monthValue == currentMonth) {
                    CalendarDay.from(it.year, it.monthValue, it.dayOfMonth)
                } else null
            }
        }.toSet()
    }

    private fun updateYearMonthText() {
        val year = binding.cvCalendar.currentDate.year
        val month = binding.cvCalendar.currentDate.month
        binding.tvCalendarYearMonth.text = getString(R.string.tv_calendar_year_month, year, month)
    }

    private val exampleItems = listOf(
        StudyActivityBriefListResDto.Item(
            id = 1,
            category = "ASSIGNMENT",
            createdAt = "2024-12-01T10:00:00",
            title = " 1",
            location = "Library",
            startDate = "2024-12-02T14:00:00",
            endDate = "2024-12-30T16:00:00",
            status = "ONGOING"
        ),
        StudyActivityBriefListResDto.Item(
            id = 2,
            category = "MEET",
            createdAt = "2024-12-30T00:00:00",
            title = "2",
            location = "Conference Room",
            startDate = "2024-12-30T15:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ),
        StudyActivityBriefListResDto.Item(
            id = 3,
            category = "MEET",
            createdAt = "2024-12-01T11:00:00",
            title = "3",
            location = "Conference Room",
            startDate = "2024-12-30T17:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ),
        StudyActivityBriefListResDto.Item(
            id = 4,
            category = "MEET",
            createdAt = "2025-12-01T11:00:00",
            title = "4",
            location = "Conference Room",
            startDate = "2024-12-31T15:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ),
        StudyActivityBriefListResDto.Item(
            id = 5,
            category = "MEET",
            createdAt = "2024-02-20T11:00:00",
            title = "5",
            location = "Conference Room",
            startDate = "2024-02-20T15:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ),
        StudyActivityBriefListResDto.Item(
            id = 6,
            category = "MEET",
            createdAt = "2024-03-20T11:00:00",
            title = "6",
            location = "Conference Room",
            startDate = "2024-03-20T11:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ),
        StudyActivityBriefListResDto.Item(
            id = 7,
            category = "DEFAULT",
            createdAt = "2024-03-20T11:00:00",
            title = "7",
            location = "Conference Room",
            startDate = "2024-03-20T11:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        )
    )
}