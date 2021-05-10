package com.newsroom.app.repository

import com.newsroom.app.api.RetrofitBuilder
import com.newsroom.app.db.ArticleDatabase
import com.newsroom.app.models.Article

class NewsRepository(val db : ArticleDatabase) {

    suspend fun getBreakingNews(countryCode : String, pageNumber : Int) =
        RetrofitBuilder.getNewsApi.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(query : String, pageNumber: Int) =
        RetrofitBuilder.getNewsApi.searchNews(query, pageNumber)

    fun getSavedArticles() = db.getArticleDAO().getAllArticles()

    suspend fun updateInsertArticle(article : Article) = db.getArticleDAO().updateInsert(article)

    suspend fun deleteArticle(article: Article) = db.getArticleDAO().deleteArticle(article)
}