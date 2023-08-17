package com.example.newsapp.network

import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.domain.model.SourceDomainModel
import com.example.newsapp.network.model.HeadlinesResponseModel


/**
 * Created by abdulbasit on 17/08/2023.
 */

fun HeadlinesResponseModel.Article.toDomainModel() = ArticleDomainModel(
    author = this.author!!,
    content = this.content!!,
    description = this.description!!,
    publishedAt = this.publishedAt!!,
    title = this.title!!,
    url = this.url!!,
    urlToImage = this.urlToImage!!,
    source = this.source?.toDomainModel()!!
)

fun HeadlinesResponseModel.Article.Source.toDomainModel() = SourceDomainModel(
    id = this.id!!,
    name = this.name!!
)