package com.newsroom.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsroom.app.models.Article
import com.newsroom.app.repository.Repository
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: Repository) : ViewModel() {

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            repository.updateInsertArticle(article)
        }
    }

}