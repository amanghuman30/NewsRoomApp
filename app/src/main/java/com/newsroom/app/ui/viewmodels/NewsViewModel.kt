package com.newsroom.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsroom.app.models.NewsApiResponse
import com.newsroom.app.ui.repository.NewsRepository
import com.newsroom.app.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var breakingNewsLiveData : MutableLiveData<Resource<NewsApiResponse>> = MutableLiveData()
    var pageNumber = 1

    init {
        getBreakingNews("us")
    }

    private fun getBreakingNews(countryCode : String) = viewModelScope.launch {
        breakingNewsLiveData.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, pageNumber)
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

}