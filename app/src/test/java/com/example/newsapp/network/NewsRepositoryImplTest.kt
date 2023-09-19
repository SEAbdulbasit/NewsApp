package com.example.newsapp.network

import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.domain.model.SourceDomainModel
import com.example.newsapp.network.model.HeadlinesResponseModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


/**
 * Created by abdulbasit on 17/08/2023.
 */

class NewsRepositoryImplTest {

    @InjectMockKs
    private lateinit var newsRepository: NewsRepositoryImpl

    @MockK
    private lateinit var newsApiInterface: NewsApiInterface

    @Before
    fun before() {
        MockKAnnotations.init(this)
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