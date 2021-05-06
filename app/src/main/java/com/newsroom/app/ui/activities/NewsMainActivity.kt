package com.newsroom.app.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.newsroom.app.R
import com.newsroom.app.db.ArticleDatabase
import com.newsroom.app.ui.NewsRepository
import com.newsroom.app.ui.NewsViewModel
import com.newsroom.app.ui.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_news_main.*

class NewsMainActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val newsViewModelFactory = NewsViewModelFactory(newsRepository)
        newsViewModel = ViewModelProvider(this, newsViewModelFactory).get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
    }
}