package com.iambedant.multiplatformsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.NewsArticle

class MainViewModel : ViewModel() {
    private val _headlines : MutableLiveData<List<NewsArticle>> = MutableLiveData()
    val headlines: LiveData<List<NewsArticle>>
        get() = _headlines

    fun updateNewsDataSource(headlines: List<NewsArticle>) {
        _headlines.value = headlines
    }
}
