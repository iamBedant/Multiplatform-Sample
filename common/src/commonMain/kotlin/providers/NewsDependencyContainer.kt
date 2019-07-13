package org.kotlin.mpp.mobile.providers

import org.kotlin.mpp.mobile.api.NetworkApi
import org.kotlin.mpp.mobile.model.DataRepositoryImpl
import org.kotlin.mpp.mobile.presentation.MainPresenter
import org.kotlin.mpp.mobile.presentation.MainView
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object NewsDependencyContainer {

    private lateinit var mainView: MainView

    private val networkApi get() = NetworkApi("https://github.com")

    private val dataRepository get() = DataRepositoryImpl(networkApi)

    val mainPresenter get() = MainPresenter(mainView, dataRepository)

    fun initialize(mainView: MainView) {
        this.mainView = mainView
    }

    inline fun <reified T: Any> inject() : T {
        return when(T::class) {
            MainPresenter::class -> mainPresenter as T
            else -> throw RuntimeException("Invalid Dependency Requested")
        }
    }
}