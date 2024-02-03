package com.example.learntime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    private val _mainLiveData = MutableLiveData<String>()
    val mainLiveData = _mainLiveData

    fun makeReq1(data : String) {
        viewModelScope.launch {
            _mainLiveData.value = mainRepository.makeReq1(data)
        }
    }
}