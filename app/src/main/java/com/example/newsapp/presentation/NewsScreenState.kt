package com.example.newsapp.presentation

import com.example.newsapp.domain.model.ArticleDomainModel


/**
 * Created by abdulbasit on 17/08/2023.
 */
data class NewsScreenState(
    val isLoading: Boolean = true,
    val articles: List<ArticleDomainModel> = emptyList(),
    val exceptionMessage: String? = null
)
