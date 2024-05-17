package com.example.pecodetestapplication.view_models

import android.app.Application
import android.app.NotificationManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pecodetestapplication.MainApplication
import com.example.pecodetestapplication.fragments.notificationList
import com.example.pecodetestapplication.numberOfPagesParam
import com.example.pecodetestapplication.repositories.PageRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PagerViewModel(
    private val pageRepository: PageRepository
) : ViewModel() {

    var setInitialNumberOfPagesJob: Job? = null

    private val _numberOfPages = MutableLiveData(1)
    val numberOfPages: MutableLiveData<Int>
        get() = _numberOfPages


    init {
        setInitialNumberOfPagesJob = viewModelScope.launch {
            numberOfPages.value = pageRepository.getParameterByKey(numberOfPagesParam) as? Int
        }
    }

    fun addPage() {
        numberOfPages.value = numberOfPages.value!! + 1
        viewModelScope.launch {
            pageRepository.updateSettings(numberOfPagesParam, numberOfPages.value!!)
        }
    }

    fun deletePage(notificationManager: NotificationManager) {
        for(id in notificationList){
            if(id.first == numberOfPages.value) {
                notificationManager.cancel(id.second)
            }
        }
        if(numberOfPages.value!! > 1) {
            numberOfPages.value = numberOfPages.value!! - 1
        }

        viewModelScope.launch {
            pageRepository.updateSettings(numberOfPagesParam, numberOfPages.value!!)
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                Log.d("myLogs", "Pager view model")
                val repository = PageRepository(MainApplication.settings)
                Log.d("myLogs", "Pager view model2")
                PagerViewModel(repository)
            }
        }
    }
}