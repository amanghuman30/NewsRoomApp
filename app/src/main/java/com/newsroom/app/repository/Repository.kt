package com.newsroom.app.repository

import androidx.lifecycle.LiveData
import com.newsroom.app.api.RetrofitBuilder
import com.newsroom.app.models.Article
import com.newsroom.app.models.NewsApiResponse
import retrofit2.Response

interface Repository {

    suspend fun getBreakingNews(countryCode : String, pageNumber : Int) : Response<NewsApiResponse>

    suspend fun searchNews(query : String, pageNumber: Int) : Response<NewsApiResponse>

    fun getSavedArticles() : LiveData<List<Article>>

    suspend fun updateInsertArticle(article : Article) : Long

    suspend fun deleteArticle(article: Article)
}