package com.iambedant.multiplatformsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.NewsArticle

class MainViewModel : ViewModel() {
    private val _headlines : MutableLiveData<List<NewsArticle>> = MutableLiveData()
    private val _bookmarked : MutableLiveData<List<NewsArticle>> = MutableLiveData()

    val bookmarkedArticle = SingleLiveEvent<NewsArticle>()

    val headlines: LiveData<List<NewsArticle>>
        get() = _headlines

    val bookmarked: LiveData<List<NewsArticle>>
        get() = _bookmarked


    fun updateNewsDataSource(headlines: List<NewsArticle>) {
        _headlines.value = headlines
    }

    fun updateBookmarkedArticles(headlines: List<NewsArticle>) {
        _bookmarked.value = headlines
    }
}
