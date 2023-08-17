package com.example.newsapp.domain.model


/**
 * Created by abdulbasit on 17/08/2023.
 */
data class ArticleDomainModel(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDomainModel,
    val title: String,
    val url: String,
    val urlToImage: String
)

data class SourceDomainModel(
    val id: String,
    val name: String
)