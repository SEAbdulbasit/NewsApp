package com.example.newsapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.model.ArticleDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * Created by abdulbasit on 17/08/2023.
 */

class NewsHeadlineDetailsViewModel(
    private val newsArticle: ArticleDomainModel
) :
    ViewModel() {
    val _uiState = MutableStateFlow(newsArticle)
    val uiState = _uiState.asStateFlow()
}

class NewsHeadlineDetailsViewModelFactory(private val newsArticle: ArticleDomainModel) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        NewsHeadlineDetailsViewModel(newsArticle) as T
}