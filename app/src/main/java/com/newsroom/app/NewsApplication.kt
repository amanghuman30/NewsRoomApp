package com.newsroom.app

import android.app.Application
import com.newsroom.app.data.di.DaggerAppComponent

class NewsApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}