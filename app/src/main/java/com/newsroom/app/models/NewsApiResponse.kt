package com.newsroom.app.models

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)