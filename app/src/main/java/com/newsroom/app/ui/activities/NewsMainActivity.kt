package com.newsroom.app.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.newsroom.app.R
import com.newsroom.app.db.ArticleDatabase
import com.newsroom.app.repository.NewsRepository
import com.newsroom.app.ui.viewmodels.NewsViewModel
import com.newsroom.app.ui.viewmodels.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_news_main.*

class NewsMainActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val newsViewModelFactory = NewsViewModelFactory(newsRepository)
        newsViewModel = ViewModelProvider(this, newsViewModelFactory).get(NewsViewModel::class.java)

        //newsViewModel.breakingNewsLiveData.observe(this, Observer {response ->
        //    Toast.makeText(this,"received in activity", Toast.LENGTH_SHORT).show()
        //})
    }
}