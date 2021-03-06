package com.newsroom.app.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.newsroom.app.R
import com.newsroom.app.viewmodels.NewsViewModel
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
            article.url?.let {
                loadUrl(it)
            }
        }

        saveFab.setOnClickListener { view ->
            newsViewModel.saveArticle(article)
            Snackbar.make(view, "Article Saved Successfully!", Snackbar.LENGTH_SHORT).show()
        }
    }
}