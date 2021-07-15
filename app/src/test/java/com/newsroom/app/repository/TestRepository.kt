package com.newsroom.app.repository

import androidx.lifecycle.LiveData
import com.newsroom.app.models.Article
import com.newsroom.app.models.NewsApiResponse
import retrofit2.Response

class TestRepository : Repository{

    private val listOfArticles : MutableList<Article> = mutableListOf(Article(id = 0, title = "Title1"))

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsApiResponse> {
        return Response.success(NewsApiResponse(listOfArticles,"",1))
    }

    override suspend fun searchNews(query: String, pageNumber: Int): Response<NewsApiResponse> {
        TODO("Not yet implemented")
    }

    override fun getSavedArticles(): LiveData<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateInsertArticle(article: Article): Long {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(article: Article) {
        TODO("Not yet implemented")
    }

}