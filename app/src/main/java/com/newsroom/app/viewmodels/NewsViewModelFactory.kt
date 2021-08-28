package com.newsroom.app.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newsroom.app.repository.NewsRepository
import com.newsroom.app.repository.Repository
import com.newsroom.app.util.BaseDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    val app : Application,
    val newsRepository : Repository,
    val dispatcher: BaseDispatchers) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository, dispatcher) as T
    }
}