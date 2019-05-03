package org.kotlin.mpp.mobile.api

import data.NewsData
import io.ktor.client.HttpClient
import io.ktor.client.features.ExpectSuccess
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.takeFrom

/**
 * Created by @iamBedant on 03,April,2019
 */

class NetworkApi(private val endPoint: String) {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(NewsData::class, NewsData.serializer())
            }
        }
        install(ExpectSuccess)
    }

    suspend fun getTopHeadlines() : NewsData = httpClient.get{
        url {
            protocol = URLProtocol.HTTPS
            host = "newsapi.org/v2"
            encodedPath = "top-headlines?country=us&apiKey=d33e891e97b74a838f165a171a07abda"
        }
    }

    private fun HttpRequestBuilder.json() {
        contentType(ContentType.Application.Json)
    }


    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}