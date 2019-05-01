package data

import kotlinx.serialization.Serializable

@Serializable
data class NewsData(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)

@Serializable
data class Source(
    val id: String?,
    val name: String?
)

@Serializable
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

data class NewsArticle(
    val author: String = "Not Available",
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "Not Available",
    val source: String = "Not Available",
    val title: String = "",
    val url: String = "",
    val urlToImage: String = ""
)