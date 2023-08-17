package com.example.newsapp.presentation.headlineslist

import app.cash.turbine.test
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.domain.model.SourceDomainModel
import com.example.newsapp.presentation.NewsScreenState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


/**
 * Created by abdulbasit on 17/08/2023.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class NewsHeadlineViewModelTest {

    private val newsRepository: NewsRepository = mockk()
    private lateinit var viewModel: NewsHeadlineViewModel

    private val exception = Exception("Something went wrong")

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun viewModelCallsGetHeadlines() = runTest {
        coEvery { newsRepository.getHeadlines() } returns Result.success(emptyList())

        viewModel = NewsHeadlineViewModel(newsRepository)

        coVerify { newsRepository.getHeadlines() }
    }

    @Test
    fun stateShouldBeLoadingAndThenSuccessWhenHeadlinesReturnsArticlesList() = runTest {
        coEvery { newsRepository.getHeadlines() } returns Result.success(emptyList())

        viewModel = NewsHeadlineViewModel(newsRepository)
        viewModel.uiState.test {
            assertEquals(NewsScreenState(), awaitItem())
            assertEquals(NewsScreenState(isLoading = false, articles = emptyList()), awaitItem())
        }
    }

    @Test
    fun stateShouldBeLoadingAndThenFailureWhenHeadlinesReturnsFailure() = runTest {
        coEvery { newsRepository.getHeadlines() } returns Result.failure(exception)

        viewModel = NewsHeadlineViewModel(newsRepository)
        viewModel.uiState.test {
            assertEquals(NewsScreenState(), awaitItem())
            assertEquals(
                NewsScreenState(
                    isLoading = false, articles = emptyList(), "Something went wrong"
                ), awaitItem()
            )
        }
    }

    @Test
    fun givenRefreshActionStateShouldBeLoadingAndTheSuccessWhenHeadlinesReturnsArticlesList() =
        runTest {

            coEvery { newsRepository.getHeadlines() } returns Result.success(emptyList())

            viewModel = NewsHeadlineViewModel(newsRepository)

            viewModel.uiState.test {
                assertEquals(NewsScreenState(), awaitItem())
                assertEquals(
                    NewsScreenState(
                        isLoading = false, articles = emptyList(), exceptionMessage = null
                    ), awaitItem()
                )

                viewModel.onAction(NewsHeadlinesActions.Refresh)

                assertEquals(
                    NewsScreenState(isLoading = true, articles = emptyList(), null), awaitItem()
                )
                assertEquals(
                    NewsScreenState(
                        isLoading = false, articles = emptyList(), exceptionMessage = null
                    ), awaitItem()
                )

            }
        }

    @Test
    fun givenRefreshActionStateShouldBeLoadingAndTheFailureWhenHeadlinesReturnsFailure() {
        runTest {
            coEvery { newsRepository.getHeadlines() } returns Result.success(listOf(articleDomainModel)) andThen Result.failure(
                exception
            )

            viewModel = NewsHeadlineViewModel(newsRepository)

            viewModel.uiState.test {
                assertEquals(NewsScreenState(), awaitItem())
                assertEquals(
                    NewsScreenState(
                        isLoading = false, articles = listOf(articleDomainModel), exceptionMessage = null
                    ), awaitItem()
                )

                viewModel.onAction(NewsHeadlinesActions.Refresh)

                assertEquals(
                    NewsScreenState(isLoading = true, articles = listOf(articleDomainModel), null), awaitItem()
                )
                assertEquals(
                    NewsScreenState(
                        isLoading = false, articles = listOf(articleDomainModel), exceptionMessage = "Something went wrong"
                    ), awaitItem()
                )

            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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