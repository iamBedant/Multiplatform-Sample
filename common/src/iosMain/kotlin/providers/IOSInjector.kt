package org.kotlin.mpp.mobile.providers

import org.kotlin.mpp.mobile.presentation.MainPresenter
import org.kotlin.mpp.mobile.providers.NewsDependencyContainer.inject

// Helpers because generics are not supported in ios compiled framework
fun injectMainPresenter() = inject<MainPresenter>()
