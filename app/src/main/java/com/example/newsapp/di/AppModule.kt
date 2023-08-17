package com.example.newsapp.di

import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.network.NewsApiInterface
import com.example.newsapp.network.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by abdulbasit on 17/08/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {
        @Provides
        fun httpClient(): OkHttpClient {
            return OkHttpClient.Builder().build()
        }

        @Provides
        fun newsApi(httpClient: OkHttpClient): NewsApiInterface {
            return Retrofit.Builder().baseUrl("https://newsapi.org/").client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create()).build()
                .create(NewsApiInterface::class.java)
        }

        @Provides
        fun newsRepository(newsApiInterface: NewsApiInterface): NewsRepository {
            return NewsRepositoryImpl(newsApiInterface)
        }
    }
}
