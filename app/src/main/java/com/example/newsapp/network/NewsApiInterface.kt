package com.example.newsapp.network

import com.example.newsapp.network.model.HeadlinesResponseModel
import retrofit2.http.GET


/**
 * Created by abdulbasit on 17/08/2023.
 */
interface NewsApiInterface {
    @GET("v2/top-headlines")
    fun getTopHeadlines(): HeadlinesResponseModel
}