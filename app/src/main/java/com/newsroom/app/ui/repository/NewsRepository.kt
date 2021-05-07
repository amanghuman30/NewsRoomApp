package com.newsroom.app.ui.repository

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.newsroom.app.api.RetrofitBuilder
import com.newsroom.app.db.ArticleDatabase

class NewsRepository(db : ArticleDatabase) {

    suspend fun getBreakingNews(countryCode : String, pageNumber : Int) =
        RetrofitBuilder.getNewsApi.getBreakingNews(countryCode, pageNumber)

}