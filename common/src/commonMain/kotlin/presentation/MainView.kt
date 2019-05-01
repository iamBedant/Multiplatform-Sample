package org.kotlin.mpp.mobile.presentation

import data.NewsArticle

/**
 * Created by @iamBedant on 03,April,2019
 */
interface MainView : BaseView{
    fun showLoader()
    fun hideLoader()
    fun displayHeadLines(headlines: List<NewsArticle>)
}