package org.kotlin.mpp.mobile.providers

import org.kotlin.mpp.mobile.model.DataRepositoryImpl
import org.kotlin.mpp.mobile.presentation.MainPresenter

// Helpers because generics are not supported in ios compiled framework
fun injectMainPresenter() = inject<MainPresenter>()
fun injectDataRespository() = inject<DataRepositoryImpl>()
