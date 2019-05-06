package org.kotlin.mpp.mobile.providers

import org.kotlin.mpp.mobile.model.DataRepositoryImpl
import org.kotlin.mpp.mobile.presentation.MainPresenter
import org.kotlin.mpp.mobile.presentation.MainView

class MainModule(private val mainView: MainView) {

    fun getPresenter() = MainPresenter(mainView)

    fun getRepository() = DataRepositoryImpl()
}