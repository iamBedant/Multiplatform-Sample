package org.kotlin.mpp.mobile.presentation

import org.kotlin.mpp.mobile.data.DisplayData

/**
 * Created by @iamBedant on 03,April,2019
 */
interface MainView : BaseView{
    fun displayData(data: DisplayData)
    fun showLoader()
    fun hideLoader()
}