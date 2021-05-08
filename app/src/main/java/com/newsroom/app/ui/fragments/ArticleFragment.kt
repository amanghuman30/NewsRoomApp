package com.newsroom.app.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.newsroom.app.R
import com.newsroom.app.ui.viewmodels.NewsViewModel
import com.newsroom.app.ui.activities.NewsMainActivity
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var newsViewModel: NewsViewModel
    val args : ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as NewsMainActivity).newsViewModel

        val article = args.articleArg

        articleWebView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

    }
}