package com.example.newsapp.network

import com.example.newsapp.BuildConfig
import com.example.newsapp.network.model.HeadlinesResponseModel
import retrofit2.http.GET


/**
 * Created by abdulbasit on 17/08/2023.
 */
interface NewsApiInterface {
    @GET("v2/top-headlines?sources=${BuildConfig.NEWS_SOURCE}&apiKey=902b695784e74a629ff6d599018719da")
    suspend fun getTopHeadlines(): HeadlinesResponseModel
}