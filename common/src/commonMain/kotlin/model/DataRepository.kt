package org.kotlin.mpp.mobile.model

import org.kotlin.mpp.mobile.data.AllData

/**
 * Created by @iamBedant on 03,April,2019
 */
interface DataRepository{
    val data : AllData?
    suspend fun getData(username:String)
}