package com.example.newsapp.network

import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.domain.model.SourceDomainModel
import com.example.newsapp.network.model.HeadlinesResponseModel
import junit.framework.TestCase.assertEquals
import org.junit.Test


/**
 * Created by abdulbasit on 17/08/2023.
 */
class MapperTest {

    @Test
    fun mapArticlesToDomainModel() {
        val articleNetworkModel = HeadlinesResponseModel.Article(
            author = "abc",
            content = "This is contnet",
            description = "This is description",
            publishedAt = "12/12/2022",
            source = HeadlinesResponseModel.Article.Source("1", "Json"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage"
        )
        assertEquals(
            ArticleDomainModel(
                author = "abc",
                content = "This is contnet",
                description = "This is description",
                publishedAt = "12/12/2022",
                source = SourceDomainModel("1", "Json"),
                title = "title",
                url = "url",
                urlToImage = "urlToImage"
            ),
            articleNetworkModel.toDomainModel()
        )
    }
}