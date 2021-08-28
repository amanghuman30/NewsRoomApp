package com.newsroom.app.data.di

import android.content.Context
import com.newsroom.app.ui.activities.NewsMainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun inject(activity: NewsMainActivity)

}