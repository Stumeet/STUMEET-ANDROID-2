package com.aramtory.stumeet.presentation.study

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramtory.stumeet.data.ApiPool
import kotlinx.coroutines.launch

class StudyViewModel : ViewModel() {
    private val _getSomething: MutableLiveData<Unit?> = MutableLiveData()
    val getSomething: MutableLiveData<Unit?> = _getSomething

    fun getTodaySwubab(time: String) = viewModelScope.launch {
        runCatching {
            ApiPool.getStudy.getSomething()
        }.fold(
            {
                _getSomething.value = it.data
            }, {
                it.message?.let { it1 -> Log.d("stumeet", it1) }
            }
        )
    }
}