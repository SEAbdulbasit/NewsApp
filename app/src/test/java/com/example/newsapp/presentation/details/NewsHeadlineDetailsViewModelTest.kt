package com.example.newsapp.presentation.details

import app.cash.turbine.test
import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.domain.model.SourceDomainModel
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * Created by abdulbasit on 18/08/2023.
 */
class NewsHeadlineDetailsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun viewmodelEmitsArticle() = runTest {

        val viewModel = NewsHeadlineDetailsViewModel(articleDomainModel)

        advanceUntilIdle()

        viewModel.uiState.test {
            TestCase.assertEquals(articleDomainModel, awaitItem())
        }
    }

    private val articleDomainModel = ArticleDomainModel(
        author = "abc",
        content = "This is content",
        description = "This is description",
        publishedAt = "12/12/2022",
        source = SourceDomainModel("1", "Json"),
        title = "title",
        url = "url",
        urlToImage = "urlToImage"
    )

}