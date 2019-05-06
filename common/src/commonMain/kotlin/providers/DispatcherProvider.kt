package org.kotlin.mpp.mobile.providers

import kotlinx.coroutines.CoroutineDispatcher

expect val getUIDispatcher: CoroutineDispatcher

expect val getBackgroundDispatcher: CoroutineDispatcher