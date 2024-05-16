package com.example.pecodetestapplication.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class PagerViewModel : ViewModel() {

    private val _numberOfPages = MutableLiveData(1)
    val numberOfPages: MutableLiveData<Int>
        get() = _numberOfPages


    fun addPage() {
        numberOfPages.value = numberOfPages.value!! + 1
    }

    fun deletePage() {
        if(numberOfPages.value!! > 1) {
            numberOfPages.value = numberOfPages.value!! - 1
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                PagerViewModel()
            }
        }
    }
}