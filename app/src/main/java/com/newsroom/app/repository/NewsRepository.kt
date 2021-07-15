package com.newsroom.app.repository

import com.newsroom.app.api.RetrofitBuilder
import com.newsroom.app.data.db.ArticleDatabase
import com.newsroom.app.models.Article

class NewsRepository(val db : ArticleDatabase) : Repository {

    override suspend fun getBreakingNews(countryCode : String, pageNumber : Int) =
        RetrofitBuilder.getNewsApi.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(query : String, pageNumber: Int) =
        RetrofitBuilder.getNewsApi.searchNews(query, pageNumber)

    override fun getSavedArticles() = db.getArticleDAO().getAllArticles()

    override suspend fun updateInsertArticle(article : Article) = db.getArticleDAO().updateInsert(article)

    override suspend fun deleteArticle(article: Article) = db.getArticleDAO().deleteArticle(article)
}