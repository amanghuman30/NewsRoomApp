package com.newsroom.app.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.newsroom.app.NewsApplication
import com.newsroom.app.data.db.ArticleDatabase
import com.newsroom.app.repository.NewsRepository
import com.newsroom.app.repository.Repository
import com.newsroom.app.util.BaseDispatchers
import com.newsroom.app.util.DispatchersContainer
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NewsModule.BindModules::class])
class NewsModule {

    @Provides
    @Singleton
    fun provideDatabase(context : Context) : ArticleDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "article_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesNewsApplication(context: Context) : Application {
        return context.applicationContext as NewsApplication
    }

    @Module
    interface BindModules {

        @Binds
        fun provideNewsRepository(repository: NewsRepository) : Repository

        @Binds
        fun provideDispatchers(container: DispatchersContainer) : BaseDispatchers
    }

}