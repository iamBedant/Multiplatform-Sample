package org.kotlin.mpp.mobile.presentation

import com.squareup.sqldelight.db.SqlDriver
import org.kotlin.mpp.mobile.data.AllData
import org.kotlin.mpp.mobile.data.DisplayData
import org.kotlin.mpp.mobile.getMainDispetcher
import org.kotlin.mpp.mobile.getSqlDeliteDriver
import org.kotlin.mpp.mobile.model.DataRepository
import org.kotlin.mpp.mobile.storage.getSavedUsername
import org.kotlin.mpp.mobile.storage.saveUserNameToDb
import org.kotlin.mpp.mobile.storage.userDatabase
import org.kotlin.mpp.mobile.utils.*
import kotlin.coroutines.CoroutineContext
import storage.Database

/**
 * Created by @iamBedant on 04,April,2019
 */

class MainPresenter(
    private val view: MainView,
    private val repository: DataRepository,
    private val uiContext: CoroutineContext = getMainDispetcher()
) {

    fun loadData(userName: String) {
        if (userName.isNullOrEmpty()) {
            view.showError(USER_NAME_NOT_VALID)
        } else {
            view.showLoader()
            launchAndCatch(uiContext, view::showError) {
                repository.getData(userName)
                showData()
            } finally {
                view.hideLoader()
            }
        }
    }

    fun initDatabase(driver: SqlDriver) {
        if (userDatabase == null) {
            userDatabase = Database(driver)
        }
    }

    fun initDatabaseIos(){
        if (userDatabase == null) {
            userDatabase = Database(getSqlDeliteDriver())
        }
    }

    private fun showData() {
        repository.data?.let {
            view.displayData(getDisplayData(it).also { saveUserNameToDb(it.name)})
        }
    }

    fun getSavedUserData(): String {
        return getSavedUsername()
    }
}



/**
 * Made this function internal to make it accessible while writing tests.
 */

internal fun getDisplayData(allData: AllData) = DisplayData(
    name = allData.name ?: allData.login,
    publicGists = "${allData.public_gists} $PUBLIC_GISTS",
    publicRepos = "${allData.public_repos} $PUBLIC_REPOS",
    avatarUrl = allData.avatar_url ?: DEFAULT_AVATAR,
    bio = allData.bio ?: NO_BIO_AVAILABLE
)
