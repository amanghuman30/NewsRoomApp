package com.newsroom.app.api

import com.newsroom.app.models.NewsApiResponse
import com.newsroom.app.util.Constants.Companion.NEWS_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode : String = "us",
        @Query("page")
        pageNum : Int = 1,
        @Query("apiKey")
        apiKey : String = NEWS_API_KEY
    ) : Response<NewsApiResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        countryCode : String,
        @Query("page")
        pageNum : Int = 1,
        @Query("apiKey")
        apiKey : String = NEWS_API_KEY
    )

}