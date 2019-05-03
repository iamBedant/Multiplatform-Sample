package org.kotlin.mpp.mobile.model

import data.NewsData
import org.kotlin.mpp.mobile.Log
import org.kotlin.mpp.mobile.api.NetworkApi
import org.kotlin.mpp.mobile.api.UnknownProblem

/**
 * Created by @iamBedant on 03,April,2019
 */

class DataRepositoryImpl : DataRepository {
    override var topHeadlines: NewsData? = null
    private val api = NetworkApi("https://github.com")

    override suspend fun getTopHeadlines() {
        val data = try {
            api.getTopHeadlines()
        } catch (cause: Throwable) {
            Log.e(cause)
            throw UnknownProblem()
        }
        this.topHeadlines = data
    }
}