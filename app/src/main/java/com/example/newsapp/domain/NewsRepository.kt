package com.example.newsapp.domain

import com.example.newsapp.domain.model.ArticleDomainModel


/**
 * Created by abdulbasit on 17/08/2023.
 */
interface NewsRepository {

    suspend fun getHeadlines(): Result<List<ArticleDomainModel>>
}