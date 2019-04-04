package org.kotlin.mpp.mobile.model

import org.kotlin.mpp.mobile.Log
import org.kotlin.mpp.mobile.api.NetworkApi
import org.kotlin.mpp.mobile.api.UnknownProblem
import org.kotlin.mpp.mobile.data.AllData

/**
 * Created by @iamBedant on 03,April,2019
 */

class DataRepositoryImpl : DataRepository {

    private val api = NetworkApi("https://github.com")
    override var data: AllData? = null

    override suspend fun getData(username: String) {

        val data = try {
            api.getAll(username)
        } catch (cause: Throwable) {
            Log.e(cause)

            throw UnknownProblem()
        }

        this.data = data

    }
}