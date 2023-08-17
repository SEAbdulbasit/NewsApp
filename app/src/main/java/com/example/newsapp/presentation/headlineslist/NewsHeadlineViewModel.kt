package com.example.newsapp.presentation.headlineslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.presentation.NewsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by abdulbasit on 17/08/2023.
 */

@HiltViewModel
class NewsHeadlineViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(NewsScreenState())
    val state = _state.asStateFlow()

    init {
        onAction(NewsHeadlinesActions.Refresh)
    }

    fun onAction(actions: NewsHeadlinesActions) {
        when (actions) {
            NewsHeadlinesActions.Refresh -> {
                fetchHeadlines()
            }
        }
    }

    private fun fetchHeadlines() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            newsRepository.getHeadlines().fold(
                onSuccess = { articlesList ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            articles = articlesList,
                            exceptionMessage = null
                        )
                    }
                },
                onFailure = { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            articles = it.articles,
                            exceptionMessage = throwable.localizedMessage
                        )
                    }
                }
            )
        }
    }
}


sealed interface NewsHeadlinesActions {
    object Refresh : NewsHeadlinesActions
}