package org.kotlin.mpp.mobile.presentation

import com.squareup.sqldelight.db.SqlDriver
import data.NewsArticle
import data.NewsData
import org.kotlin.mpp.mobile.getMainDispetcher
import org.kotlin.mpp.mobile.getSqlDeliteDriver
import org.kotlin.mpp.mobile.model.DataRepository
import org.kotlin.mpp.mobile.storage.getSavedUsername
import org.kotlin.mpp.mobile.storage.userDatabase
import org.kotlin.mpp.mobile.utils.*
import storage.Database
import kotlin.coroutines.CoroutineContext

/**
 * Created by @iamBedant on 04,April,2019
 */

class MainPresenter(
    private val view: MainView,
    private val repository: DataRepository,
    private val uiContext: CoroutineContext = getMainDispetcher()) {

    fun initDatabase(driver: SqlDriver) {
        if (userDatabase == null) {
            userDatabase = Database(driver)
        }
    }

    fun initDatabaseIos() {
        if (userDatabase == null) {
            userDatabase = Database(getSqlDeliteDriver())
        }
    }

    fun getSavedUserData(): String {
        return getSavedUsername()
    }

    fun loadTopHeadlines() {
        view.showLoader()
        launchAndCatch(uiContext, view::showError) {
            repository.getTopHeadlines()
            displayHeadlines()
        } finally {
            view.hideLoader()
        }
    }

    private fun displayHeadlines() {
        repository.topHeadlines?.let {
            view.displayHeadLines(getHeadlinesData(it))
        }
    }
}


/**
 * Made this function internal to make it accessible while writing tests.
 */
internal fun getHeadlinesData(newsData: NewsData): List<NewsArticle> {
    val newsArticle = mutableListOf<NewsArticle>()
    newsData.articles?.let {
        it.forEach {
            newsArticle.add(
                NewsArticle(
                    author = it.author ?: "",
                    source = it.source?.name ?: "",
                    title = it.title ?: "",
                    description = it.description ?: "",
                    publishedAt = it.publishedAt ?: "",
                    content = it.content ?: "",
                    url = it.url ?: "",
                    urlToImage = it.urlToImage ?: ""
                )
            )
        }
    }
    return newsArticle
}