package com.example.pecodetestapplication.view_models

import android.app.NotificationManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pecodetestapplication.MainApplication
import com.example.pecodetestapplication.currentNotificationIdParam
import com.example.pecodetestapplication.fragments.currentNotificationId
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
            numberOfPages.value = pageRepository.getParameterByKey(numberOfPagesParam) as? Int ?: 1
            currentNotificationId = pageRepository.getParameterByKey(currentNotificationIdParam) as? Int ?: 0
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

    fun saveCurrentNotificationId() {
        viewModelScope.launch {
            pageRepository.updateSettings(currentNotificationIdParam, currentNotificationId)
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val repository = PageRepository(MainApplication.settings)
                PagerViewModel(repository)
            }
        }
    }
}