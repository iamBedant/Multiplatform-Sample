package org.kotlin.mpp.mobile

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import storage.Database

/**
 * Created by @iamBedant on 03,April,2019
 */

actual object Log {
    actual fun d(message: String) {
        print(message)
    }
    actual fun e(message: String) {
        print(message)
    }

    actual fun i(message: String) {
        print(message)
    }

    actual fun e(error: Throwable) {
        print(error)
    }
}

object MainLoopDispatcher: CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        NSRunLoop.mainRunLoop().performBlock {
            block.run()
        }
    }
}

actual fun getMainDispatcher(): CoroutineDispatcher {
    return MainLoopDispatcher
}

actual fun <T> runTest(block: suspend () -> T) {
    runBlocking { block() }
}

actual fun getSqlDeliteDriver(): SqlDriver = NativeSqliteDriver(Database.Schema, "user.db")