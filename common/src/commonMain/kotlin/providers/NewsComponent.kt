package org.kotlin.mpp.mobile.providers

import org.kotlin.mpp.mobile.model.DataRepositoryImpl
import org.kotlin.mpp.mobile.presentation.MainPresenter
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object NewsComponent {

    lateinit var mainModule : MainModule

    fun addModule(mainModule: MainModule) {
        this.mainModule = mainModule
    }
}

// Add some sort of factory here
inline fun <reified T: Any> inject() : T {
    return when(T::class) {
        MainPresenter::class -> NewsComponent.mainModule.getPresenter() as T
        DataRepositoryImpl::class -> NewsComponent.mainModule.getRepository() as T
        else -> Any() as T
    }
}