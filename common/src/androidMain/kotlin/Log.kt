package org.kotlin.mpp.mobile

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
/**
 * Created by @iamBedant on 03,April,2019
 */

actual object Log{
    actual fun d(message: String) {
//        Timber.d(message)
    }

    actual fun e(message: String) {
//        Timber.e(message)
    }

    actual fun i(message: String) {
//        Timber.i(message)
    }

    actual fun e(error: Throwable) {
//        Timber.e(error)
    }
}
actual fun getMainDispetcher(): CoroutineDispatcher {
    return Dispatchers.Main
}

actual fun <T> runTest(block: suspend () -> T) {
    runBlocking { block() }
}

