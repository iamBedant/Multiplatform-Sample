package org.kotlin.mpp.mobile.storage

import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import storage.Database

fun getNativeSqlDriver() = NativeSqliteDriver(Database.Schema, "article.db")