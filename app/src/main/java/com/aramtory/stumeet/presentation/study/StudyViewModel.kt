package com.aramtory.stumeet.presentation.study

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudyViewModel : ViewModel() {
    private val _getSomething: MutableLiveData<Unit?> = MutableLiveData()
    val getSomething: MutableLiveData<Unit?> = _getSomething

    /**
    fun getSomething(time: String) = viewModelScope.launch {
    runCatching {
    BaseApiPool.getStudyActivity.getSomething()
    }.fold(
    {
    _getSomething.value = it.data
    }, {
    it.message?.let { it1 -> Log.d("stumeet", it1) }
    }
    )
    }
     **/
}