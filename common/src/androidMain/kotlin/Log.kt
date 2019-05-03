package org.kotlin.mpp.mobile

import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
/**
 * Created by @iamBedant on 03,April,2019
 */

actual object Log{
    actual fun d(message: String) {
        System.out.println(message)
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
actual fun getMainDispatcher(): CoroutineDispatcher {
    return Dispatchers.Main
}

actual fun <T> runTest(block: suspend () -> T) {
    runBlocking { block() }
}

actual fun getSqlDeliteDriver(): SqlDriver {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    //TODO: figure out a way to provide android driver from here
}