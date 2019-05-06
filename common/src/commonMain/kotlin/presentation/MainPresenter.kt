package org.kotlin.mpp.mobile.presentation

import com.squareup.sqldelight.db.SqlDriver
import data.NewsArticle
import org.kotlin.mpp.mobile.getSqlDeliteDriver
import org.kotlin.mpp.mobile.model.DataRepositoryImpl
import org.kotlin.mpp.mobile.providers.inject
import org.kotlin.mpp.mobile.storage.newsDatabase
import org.kotlin.mpp.mobile.utils.mapToNewsArticleList
import storage.BookmarkedArticle
import storage.Database

/**
 * Created by @iamBedant on 04,April,2019
 */

class MainPresenter(private val view: MainView) {

    // We should be able to inject MainView as well. Will see that later.
    private val dataRepository: DataRepositoryImpl by lazy { inject<DataRepositoryImpl>() }

    fun initDatabase(driver: SqlDriver) {
        if (newsDatabase == null) {
            newsDatabase = Database(driver)
        }
    }
    fun loadTopHeadlines() {
        view.showLoader()
        dataRepository.getTopHeadLines {
            view.displayHeadLines(it.mapToNewsArticleList())
            view.hideLoader()
        }
    }

    fun storeArticle(newsArticle: NewsArticle) {
        dataRepository.saveArticle(newsArticle)
    }

    fun getStoredArticles(callback : (List<BookmarkedArticle>) -> Unit) {
        dataRepository.getStoredArticles(callback)
    }
}