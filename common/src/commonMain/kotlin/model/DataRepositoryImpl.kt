package org.kotlin.mpp.mobile.model

import data.NewsArticle
import data.NewsData
import org.kotlin.mpp.mobile.Log
import org.kotlin.mpp.mobile.api.NetworkApi
import org.kotlin.mpp.mobile.api.UnknownProblem
import org.kotlin.mpp.mobile.storage.newsDatabase
import org.kotlin.mpp.mobile.utils.mapToBookmarkedArticle
import org.kotlin.mpp.mobile.utils.runAsync
import org.kotlin.mpp.mobile.utils.runAsyncSuspend
import storage.BookmarkedArticle

/**
 * Created by @iamBedant on 03,April,2019
 */

class DataRepositoryImpl(private val networkApi: NetworkApi) : DataRepository {
    override var topHeadlines: NewsData? = null

    // Remove this function later
    override suspend fun getTopHeadlines() {
        val data = try {
            networkApi.getTopHeadlines()
        } catch (cause: Throwable) {
            Log.e(cause)
            throw UnknownProblem()
        }
        this.topHeadlines = data
    }

    fun getTopHeadLines(block : (NewsData) -> Unit) {
        runAsyncSuspend( { networkApi.getTopHeadlines() }, { newsData ->
            newsData?.let { block.invoke(it) }
        })
    }

    fun getStoredArticles(callback : (List<BookmarkedArticle>) -> Unit) {
        runAsync({ newsDatabase?.bookmarkedArticleQueries?.getAll()?.executeAsList() }, { bookMarkedArticles ->
            bookMarkedArticles?.let {
                callback.invoke(it)
            }
        })
    }

    fun saveArticle(newsArticle: NewsArticle) {
        val bookmarkedArticle = newsArticle.mapToBookmarkedArticle()
        runAsync {
            newsDatabase?.bookmarkedArticleQueries?.insert(
                "author",
                bookmarkedArticle.content,
                bookmarkedArticle.description,
                bookmarkedArticle.publishedAt,
                "sourceid",
                "sourcename",
                bookmarkedArticle.title,
                bookmarkedArticle.url,
                bookmarkedArticle.urlToImage
            )
        }
    }

}