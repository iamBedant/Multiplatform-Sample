package org.kotlin.mpp.mobile

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Created by @iamBedant on 03,April,2019
 */

actual object Log {
    actual fun d(message: String) {
    }

    actual fun e(error: Throwable) {
    }

    actual fun e(message: String) {
    }

    actual fun i(message: String) {
    }
}
actual fun getMainDispetcher(): CoroutineDispatcher {
    return Dispatchers.Main
}

actual fun <T> runTest(block: suspend () -> T) {
    runBlocking { block() }
}