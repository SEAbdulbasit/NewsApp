package com.example.newsapp.network

import com.example.newsapp.network.model.HeadlinesResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


/**
 * Created by abdulbasit on 17/08/2023.
 */
interface NewsApiInterface {
    @GET("v2/top-headlines?country=us&apiKey=902b695784e74a629ff6d599018719da")
    suspend fun getTopHeadlines(): HeadlinesResponseModel
}