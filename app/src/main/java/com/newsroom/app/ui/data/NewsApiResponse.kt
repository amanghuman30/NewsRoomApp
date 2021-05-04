package com.newsroom.app.ui.data

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)