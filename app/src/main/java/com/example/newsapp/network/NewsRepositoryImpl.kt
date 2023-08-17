package com.example.newsapp.network

import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.ArticleDomainModel


/**
 * Created by abdulbasit on 17/08/2023.
 */
class NewsRepositoryImpl(private val newsApiInterface: NewsApiInterface) : NewsRepository {
    override suspend fun getHeadlines(): Result<List<ArticleDomainModel>> {
        return try {
            val result = newsApiInterface.getTopHeadlines().articles.map { it.toDomainModel() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


