package org.kotlin.mpp.mobile.model

import data.NewsData

/**
 * Created by @iamBedant on 03,April,2019
 */
interface DataRepository{
    val topHeadlines : NewsData ?
    suspend fun getTopHeadlines()
}