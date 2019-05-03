package com.iambedant.multiplatformsample

import android.app.Application
import timber.log.Timber

/**
 * Created by @iamBedant on 04,April,2019
 */
class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
        }
    }
}