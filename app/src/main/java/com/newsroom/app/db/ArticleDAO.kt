package com.newsroom.app.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.newsroom.app.models.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article : Article) : Long

    @Query("Select * from article")
    fun getAllArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}