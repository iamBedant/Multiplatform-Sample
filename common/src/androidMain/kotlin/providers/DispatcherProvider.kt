package org.kotlin.mpp.mobile.providers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val getUIDispatcher: CoroutineDispatcher = Dispatchers.Main

actual val getBackgroundDispatcher = Dispatchers.Default