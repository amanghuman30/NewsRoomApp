package com.newsroom.app.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.newsroom.app.R
import com.newsroom.app.ui.NewsViewModel
import com.newsroom.app.ui.activities.NewsMainActivity

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as NewsMainActivity).newsViewModel
    }
}