package com.newsroom.app.ui.api

import com.newsroom.app.ui.data.NewsApiResponse
import com.newsroom.app.ui.util.Constants
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
        apiKey : String = Constants.NEWS_API_KEY
    ) : Response<NewsApiResponse>

    @GET("v2/top-headlines")
    suspend fun searchNews(
        @Query("q")
        countryCode : String,
        @Query("page")
        pageNum : Int = 1,
        @Query("apiKey")
        apiKey : String = Constants.NEWS_API_KEY
    )

}