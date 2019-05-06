package org.kotlin.mpp.mobile.utils

import data.Article
import data.NewsArticle
import data.NewsData
import storage.BookmarkedArticle

fun Article.mapToBookmarkedArticle(): BookmarkedArticle {
    return BookmarkedArticle.Impl(
        author = author.orEmpty(),
        content = content.orEmpty(),
        description = description.orEmpty(),
        sourceId = source?.id.orEmpty(),
        sourceName = source?.name.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        title = title.orEmpty(),
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty()
    )
}


fun NewsArticle.mapToBookmarkedArticle(): BookmarkedArticle {
    return BookmarkedArticle.Impl(
        author = author,
        content = content,
        description = description,
        sourceId = "",
        sourceName = "",
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun BookmarkedArticle.mapToNewsArticle(): NewsArticle {
    return NewsArticle(
        author = author,
        description = description,
        content = content,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun NewsData.mapToNewsArticleList(): List<NewsArticle> {
    return articles?.let { articleList ->
        articleList.map {
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
        }
    } ?: emptyList()
}