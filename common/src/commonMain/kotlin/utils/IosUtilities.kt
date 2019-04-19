package org.kotlin.mpp.mobile.utils

import org.kotlin.mpp.mobile.getMainDispetcher

/**
 * Created by @iamBedant on 04,April,2019
 */

class IosUtilities {

    /**
     * A hack to provide dispetcher to iOS
     */

    fun getDispetcher() = getMainDispetcher()

}