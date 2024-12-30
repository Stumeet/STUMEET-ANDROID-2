package com.aramtory.stumeet.presentation.home

import android.view.View
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.component.activity_list.fragment.ActivityBriefListFragment
import com.aramtory.stumeet.coreui.component.activity_list.fragment.ActivityDetailListFragment
import com.aramtory.stumeet.coreui.view.calculateDurationBetweenCurrentTime
import com.aramtory.stumeet.coreui.view.toLocalDateTime
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityBriefListResDto
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityDetailListResDto
import com.aramtory.stumeet.data.enums.StudyActivityType
import com.aramtory.stumeet.databinding.FragmentHomeBinding
import com.aramtory.stumeet.presentation.home.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import java.time.LocalDateTime

class HomeFragment :
    com.aramtory.stumeet.coreui.base.BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initView() {
        // 곧 시작 활동 초기화
        initializeStartActivity()

        // tab layout + viewpager2 초기화
        initializeTabAndViewPager()
    }

    override fun onResume() {
        super.onResume()
        initializeTabAndViewPager()
    }

    private fun initializeStartActivity() {
        with(binding) {
            tvRecentActivityTitle.text = exampleBriefItem.title
            tvRecentActivityStudyName.text = exampleBriefItem.category // TODO 스터디 주시면 바꾸기
            val remainTime = StudyActivityType.doWhenStudyActivityType(
                category = exampleBriefItem.category,
                whenMeet = {
                    exampleBriefItem.startDate?.calculateDurationBetweenCurrentTime(requireContext())
                        ?: exampleBriefItem.startDate?.calculateDurationBetweenCurrentTime(
                            requireContext(),
                            false
                        )
                },
                whenAssignment = {
                    exampleBriefItem.endDate?.calculateDurationBetweenCurrentTime(requireContext())
                        ?: exampleBriefItem.startDate?.calculateDurationBetweenCurrentTime(
                            requireContext(),
                            false
                        )
                },
                whenDefault = {
                    binding.clHomeRecentActivity.visibility = View.GONE
                    null
                }
            ) ?: ""
            tvRecentActivityReaminTime.text = remainTime
        }
    }

    private fun initializeTabAndViewPager() {
        val adapter = initializeHomePagerAdapter()
        with(binding) {
            vpHomeList.adapter = adapter
            vpHomeList.offscreenPageLimit = 2 // 한번에 2개의 fragment 초기화
            setTabLayoutWithViewPager()
            vpHomeList.post {
                setDefaultData(adapter, exampleBriefItems, exampleDetailItems)
            }
        }
    }

    private fun initializeHomePagerAdapter(): HomePagerAdapter {
        return HomePagerAdapter(childFragmentManager, lifecycle, onClick = {
            // TODO 활동 상세 화면으로 이동
        })
    }

    private fun setTabLayoutWithViewPager() {
        TabLayoutMediator(binding.tlHomeTab, binding.vpHomeList) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tbt_home_todo)
                1 -> getString(R.string.tbt_home_notice)
                else -> getString(R.string.tbt_home_todo)
            }
        }.attach()
    }

    private fun setDefaultData(
        adapter: HomePagerAdapter,
        briefListData: List<StudyActivityBriefListResDto.Item>?,
        detailListData: List<StudyActivityDetailListResDto.Item>?
    ) {
        // Brief 데이터 설정
        val briefFragment = adapter.getFragment(0) as? ActivityBriefListFragment
        setBriefData(briefFragment, briefListData)

        // Detail 데이터 설정
        val detailFragment = adapter.getFragment(1) as? ActivityDetailListFragment
        setDetailData(detailFragment, detailListData)
    }

    private fun setBriefData(
        briefFragment: ActivityBriefListFragment?,
        briefListData: List<StudyActivityBriefListResDto.Item>?,
    ) {
        val now = LocalDateTime.now()

        val filteredItems = briefListData?.filter { item ->
            StudyActivityType.doWhenStudyActivityType(
                category = item.category,
                whenMeet = {
                    item.startDate?.toLocalDateTime()?.isAfter(now)
                },
                whenAssignment = {
                    item.endDate?.toLocalDateTime()?.isAfter(now)
                },
                whenDefault = {
                    false
                }
            ) ?: false
        }

        if (filteredItems == null) {
            briefFragment?.setVisibility(false)
        } else {
            briefFragment?.setVisibility(true)
            briefFragment?.submitList(filteredItems)
        }
    }

    private fun setDetailData(
        detailFragment: ActivityDetailListFragment?,
        detailListData: List<StudyActivityDetailListResDto.Item>?,
    ) {
        if (detailListData == null) {
            detailFragment?.setVisibility(false)
        } else {
            detailFragment?.setVisibility(true)
            detailFragment?.submitList(detailListData)
        }
    }

    private val exampleDetailItems = listOf(
        StudyActivityDetailListResDto.Item(
            id = 1,
            author = StudyActivityDetailListResDto.Item.Author(
                memberId = 101,
                name = "홍길동",
                profileImageUrl = "https://i.pinimg.com/736x/67/c7/38/67c738b5196cd242675efc754aa143aa.jpg"
            ),
            category = "ASSIGNMENT",
            content = "Android Kotlin 프로젝트 관리하기",
            createdAt = "2024-12-27T10:00:00",
            location = "서울시 강남구",
            startDate = "2024-12-27T13:00:00",
            endDate = "2024-12-30T16:00:00",
            title = "Android 스터디 세션"
        ), StudyActivityDetailListResDto.Item(
            id = 2,
            author = StudyActivityDetailListResDto.Item.Author(
                memberId = 102,
                name = "김철수",
                profileImageUrl = "https://i.pinimg.com/736x/9c/86/b6/9c86b65707283ae3aae18849a3d2548f.jpg"
            ),
            category = "MEET",
            content = "프로젝트 일정 점검",
            createdAt = "2024-12-28T15:30:00",
            location = "온라인 Zoom",
            startDate = "2024-12-31T10:00:00",
            endDate = "2024-12-31T12:00:00",
            title = "프로젝트 회의"
        ), StudyActivityDetailListResDto.Item(
            id = 3,
            author = StudyActivityDetailListResDto.Item.Author(
                memberId = 104,
                name = "김철수",
                profileImageUrl = "https://i.pinimg.com/736x/9c/86/b6/9c86b65707283ae3aae18849a3d2548f.jpg"
            ),
            category = "ASSIGNMENT",
            content = "프로젝트 일정 점검트 일정 점검트 일정 점검트 일정 점검트 일정 점검트 일정 점검트 일정 점검트 일정 점검트 일정 점검",
            createdAt = "2024-12-28T15:30:00",
            location = "온라인 Zoom",
            startDate = "2024-12-31T10:00:00",
            endDate = "2024-12-31T12:00:00",
            title = "프로젝트 회의"
        ), StudyActivityDetailListResDto.Item(
            id = 3,
            author = null,
            category = "MEET",
            content = "프로젝트 일정 점검",
            createdAt = "2024-12-28T15:30:00",
            location = "온라인 Zoom",
            startDate = "2024-12-31T10:00:00",
            endDate = "2024-12-31T12:00:00",
            title = "프로젝트 회의"
        ), StudyActivityDetailListResDto.Item(
            id = 3,
            author = null,
            category = "DEFAULT",
            content = "프로젝트 일정 점검",
            createdAt = "2024-12-28T15:30:00",
            location = "온라인 Zoom",
            startDate = "2024-12-31T10:00:00",
            endDate = "2024-12-31T12:00:00",
            title = "프로젝트 회의"
        )
    )

    private val exampleBriefItem = StudyActivityBriefListResDto.Item(
        id = 1,
        category = "ASSIGNMENT",
        createdAt = "2024-12-01T10:00:00",
        title = "1ASSIGNMENT1ASSIGNMENT1ASSIGNMENT",
        location = "Library",
        startDate = "2024-12-02T14:00:00",
        endDate = "2024-12-29T16:00:00",
        status = "ONGOING"
    )

    private val exampleBriefItems = listOf(
        StudyActivityBriefListResDto.Item(
            id = 1,
            category = "ASSIGNMENT",
            createdAt = "2024-12-01T10:00:00",
            title = " 1",
            location = "Library",
            startDate = "2024-12-02T14:00:00",
            endDate = "2024-12-29T16:00:00",
            status = "ONGOING"
        ), StudyActivityBriefListResDto.Item(
            id = 2,
            category = "MEET",
            createdAt = "2024-12-30T00:00:00",
            title = "2",
            location = "Conference Room",
            startDate = "2024-12-30T15:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ), StudyActivityBriefListResDto.Item(
            id = 3,
            category = "MEET",
            createdAt = "2024-12-01T11:00:00",
            title = "3",
            location = "Conference Room",
            startDate = "2024-12-30T17:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ), StudyActivityBriefListResDto.Item(
            id = 4,
            category = "MEET",
            createdAt = "2025-12-01T11:00:00",
            title = "4",
            location = "Conference Room",
            startDate = "2024-12-31T15:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ), StudyActivityBriefListResDto.Item(
            id = 5,
            category = "MEET",
            createdAt = "2024-02-20T11:00:00",
            title = "5",
            location = "Conference Room",
            startDate = "2024-02-20T15:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ), StudyActivityBriefListResDto.Item(
            id = 6,
            category = "MEET",
            createdAt = "2024-03-20T11:00:00",
            title = "6",
            location = "Conference Room",
            startDate = "2024-03-20T11:00:00",
            endDate = "2024-12-03T16:30:00",
            status = "UPCOMING"
        ), StudyActivityBriefListResDto.Item(
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