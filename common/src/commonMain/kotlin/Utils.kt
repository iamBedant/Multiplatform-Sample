package org.kotlin.mpp.mobile

import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by @iamBedant on 03,April,2019
 */

expect object Log{
    fun d(message: String)
    fun e(error:Throwable)
    fun e(message: String)
    fun i(message: String)
}

expect fun getMainDispetcher(): CoroutineDispatcher

expect fun <T> runTest(block: suspend () -> T)

expect fun getSqlDeliteDriver(): SqlDriver