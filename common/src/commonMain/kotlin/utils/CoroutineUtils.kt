package org.kotlin.mpp.mobile.utils

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.providers.getBackgroundDispatcher
import org.kotlin.mpp.mobile.providers.getUIDispatcher
import kotlin.coroutines.CoroutineContext

/**
 * Created by @iamBedant on 04,April,2019
 */

fun launchAndCatch(
    context: CoroutineContext,
    onError: (String) -> Unit,
    function: suspend () -> Unit
): Finalizable {
    val ret = Finalizable()
    GlobalScope.launch(context) {
        try {
            function()
        } catch (e: Throwable) {
            onError(e.message?: GENERIC_ERROR_MESSAGE)
        } finally {
            ret.onFinally?.invoke()
        }
    }

    return ret
}

class Finalizable {
    var onFinally: (() -> Unit)? = null

    infix fun finally(f: () -> Unit) {
        onFinally = f
    }
}

fun <T> runAsync(heavyFunction: () -> T, response : (response : T?) -> Unit) {
    // For iOS, background dispatcher will itself switch context to main thread,
    // so here there is an extra context switching but it should not be expensive as there is no thread creation
    GlobalScope.launch(getUIDispatcher) {
        val deferred = GlobalScope.async(getBackgroundDispatcher) {
            heavyFunction.invoke()
        }
        response.invoke(deferred.await())
    }
}

fun <T> runAsyncSuspend(heavyFunction: suspend () -> T, response : (response : T?) -> Unit) {
    GlobalScope.launch(getBackgroundDispatcher) {
        val result = heavyFunction.invoke()
        GlobalScope.launch(getUIDispatcher) {
            response.invoke(result)
        }
    }
}

fun <T> runAsync(heavyFunction: () -> T) {
    GlobalScope.launch(getBackgroundDispatcher) {
        heavyFunction.invoke()
    }
}