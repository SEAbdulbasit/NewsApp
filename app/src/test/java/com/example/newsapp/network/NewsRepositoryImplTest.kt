package com.example.newsapp.network

import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.domain.model.SourceDomainModel
import com.example.newsapp.network.model.HeadlinesResponseModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


/**
 * Created by abdulbasit on 17/08/2023.
 */

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryImplTest {

    private val newsApiInterface: NewsApiInterface = mockk()
    private lateinit var newsRepository: NewsRepository

    @Before
    fun before() {
        newsRepository = NewsRepositoryImpl(newsApiInterface)
    }

    @Test
    fun getHeadlinesReturnsArticlesList() = runTest {
        coEvery { newsApiInterface.getTopHeadlines() } returns headlinesResponse

        val result = newsRepository.getHeadlines()

        assertEquals(true, result.isSuccess)
        assertEquals(listOf(articleDomainModel), result.getOrNull())
    }

    @Test
    fun getHeadlinesThrowsException() = runTest {
        coEvery { newsApiInterface.getTopHeadlines() } throws Exception("Something went wrong")

        val result = newsRepository.getHeadlines()

        assertEquals(true, result.isFailure)
        assertEquals("Something went wrong", result.exceptionOrNull()?.message)
    }


    private val headlinesResponse = HeadlinesResponseModel(
        articles = listOf(
            HeadlinesResponseModel.Article(
                author = "abc",
                content = "This is contnet",
                description = "This is description",
                publishedAt = "12/12/2022",
                source = HeadlinesResponseModel.Article.Source("1", "Json"),
                title = "title",
                url = "url",
                urlToImage = "urlToImage"
            )
        ), status = "ok", totalResults = 1
    )

    private val articleDomainModel = ArticleDomainModel(
        author = "abc",
        content = "This is contnet",
        description = "This is description",
        publishedAt = "12/12/2022",
        source = SourceDomainModel("1", "Json"),
        title = "title",
        url = "url",
        urlToImage = "urlToImage"
    )

}