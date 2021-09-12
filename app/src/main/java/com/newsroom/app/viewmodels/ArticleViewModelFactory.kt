package com.newsroom.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newsroom.app.data.di.FeatureScope
import com.newsroom.app.repository.Repository
import javax.inject.Inject

@FeatureScope
class ArticleViewModelFactory @Inject constructor(val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(repository) as T
    }
}