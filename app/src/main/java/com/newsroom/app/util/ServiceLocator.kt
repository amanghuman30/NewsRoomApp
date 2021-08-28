package com.newsroom.app.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.newsroom.app.data.db.ArticleDatabase
import com.newsroom.app.repository.NewsRepository
import com.newsroom.app.repository.Repository

object ServiceLocator {

    @Volatile
    var newsRepository : Repository? = null
    @VisibleForTesting set

    private val lock = Any()

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            newsRepository = null
        }
    }

    fun provideRepository(context: Context) : Repository{
        synchronized(this) {
            return newsRepository ?: NewsRepository(
                Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_database.db"
            ).build()).also {
                newsRepository = it
            }
        }
    }

}