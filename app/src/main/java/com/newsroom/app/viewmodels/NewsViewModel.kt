package com.newsroom.app.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.newsroom.app.NewsApplication
import com.newsroom.app.models.Article
import com.newsroom.app.models.NewsApiResponse
import com.newsroom.app.repository.NewsRepository
import com.newsroom.app.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(app : Application,
                    val newsRepository: NewsRepository) : AndroidViewModel(app) {

    var breakingNewsLiveData : MutableLiveData<Resource<NewsApiResponse>> = MutableLiveData()
    var breakingNewsPageNumber = 1
    var searchNewsPageNumber = 1

    var searchNewsLiveData : MutableLiveData<Resource<NewsApiResponse>> = MutableLiveData()

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode : String) = viewModelScope.launch {
        breakingNewsLiveData.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPageNumber)
        breakingNewsLiveData.postValue(handleBreakingNewsResponse(response))
        breakingNewsPageNumber++
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

     private fun isInternetConnected() : Boolean {
         val networkManager = getApplication<NewsApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             val activeNetwork = networkManager.activeNetwork ?: return false
             val networkCapabilities = networkManager.getNetworkCapabilities(activeNetwork) ?: return false

             return when {
                 networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                 networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                 networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                 else -> false
             }
         } else {
             networkManager.activeNetworkInfo.run {
                 return when(type) {
                     ConnectivityManager.TYPE_WIFI -> true
                     ConnectivityManager.TYPE_MOBILE -> true
                     ConnectivityManager.TYPE_ETHERNET -> true
                     else -> false
                 }
             }
         }
     }

}