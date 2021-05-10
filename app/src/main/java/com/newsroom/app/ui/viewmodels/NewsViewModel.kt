package com.newsroom.app.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsroom.app.models.Article
import com.newsroom.app.models.NewsApiResponse
import com.newsroom.app.repository.NewsRepository
import com.newsroom.app.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    var breakingNewsLiveData : MutableLiveData<Resource<NewsApiResponse>> = MutableLiveData()
    var breakingNewsPageNumber = 1
    var searchNewsPageNumber = 1

    var searchNewsLiveData : MutableLiveData<Resource<NewsApiResponse>> = MutableLiveData()

    init {
        getBreakingNews("us")
    }

    private fun getBreakingNews(countryCode : String) = viewModelScope.launch {
        breakingNewsLiveData.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPageNumber)
        breakingNewsLiveData.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response : Response<NewsApiResponse>): Resource<NewsApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
                return Resource.Success(newsResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun searchNews(query : String) = viewModelScope.launch {
        searchNewsLiveData.postValue(Resource.Loading())
        val response = newsRepository.searchNews(query, searchNewsPageNumber)
        searchNewsLiveData.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response : Response<NewsApiResponse>) : Resource<NewsApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
                return Resource.Success(newsResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.updateInsertArticle(article)
        }
    }

    fun deleteArticle(article : Article) {
        viewModelScope.launch {
            newsRepository.deleteArticle(article)
        }
    }

}