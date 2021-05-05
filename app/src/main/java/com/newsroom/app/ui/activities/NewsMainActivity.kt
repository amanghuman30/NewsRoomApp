package com.newsroom.app.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.newsroom.app.R
import kotlinx.android.synthetic.main.activity_news_main.*

class NewsMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

    }
}