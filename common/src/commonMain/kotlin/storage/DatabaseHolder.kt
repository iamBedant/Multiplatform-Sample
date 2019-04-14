package org.kotlin.mpp.mobile.storage

import org.kotlin.mpp.mobile.Log
import storage.Database

var userDatabase: Database ? = null

// TODO: make it async with coroutines
fun saveUserNameToDb(userName: String) {
    userDatabase?.let {
        val users = it.userQueries.getAll().executeAsList()
        if (!users.contains(userName)) {
            it.userQueries.insert(userName)
            Log.d("Saved $userName to db")
        } else {
            Log.d("$userName already present in db")
        }
    }

}