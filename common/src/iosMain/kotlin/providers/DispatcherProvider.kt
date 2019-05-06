package org.kotlin.mpp.mobile.providers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

actual val getUIDispatcher: CoroutineDispatcher = NSUIDispatcher()

actual val getBackgroundDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

/**
 * This dispatcher will run block on UI thread for iOS.
 */
internal class NSUIDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        NSRunLoop.mainRunLoop().performBlock {
            block.run()
        }
    }
}

/**
 * This dispatcher will run block in background but it also switches back to main thread.
 * This is also only dispatcher which I could make it work.
 */
internal class NsQueueDispatcher(private val dispatchQueue: dispatch_queue_t) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}

// Doesn't work for now because of kotlin native threading model :(
@kotlin.ExperimentalUnsignedTypes
internal class NSBackgroundDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0uL)) {
            block.run()
        }
    }
}