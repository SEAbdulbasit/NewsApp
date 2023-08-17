package com.example.newsapp.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by abdulbasit on 17/08/2023.
 */
@JsonClass(generateAdapter = true)
data class HeadlinesResponseModel(
    @Json(name = "articles") val articles: List<Article>,
    @Json(name = "status") val status: String,
    @Json(name = "totalResults") val totalResults: Int
) {
    @JsonClass(generateAdapter = true)
    data class Article(
        @Json(name = "author") val author: String?,
        @Json(name = "content") val content: String?,
        @Json(name = "description") val description: String?,
        @Json(name = "publishedAt") val publishedAt: String?,
        @Json(name = "source") val source: Source,
        @Json(name = "title") val title: String?,
        @Json(name = "url") val url: String?,
        @Json(name = "urlToImage") val urlToImage: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Source(
            @Json(name = "id") val id: String?,
            @Json(name = "name") val name: String
        )
    }
}