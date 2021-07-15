package com.newsroom.app.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newsroom.app.repository.NewsRepository
import com.newsroom.app.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher

class NewsViewModelFactory(val app : Application, val newsRepository : Repository, val dispatcher: CoroutineDispatcher) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository, dispatcher) as T
    }
}