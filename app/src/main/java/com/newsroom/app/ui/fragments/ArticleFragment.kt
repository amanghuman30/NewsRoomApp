package com.newsroom.app.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.newsroom.app.NewsApplication
import com.newsroom.app.R
import com.newsroom.app.viewmodels.ArticleViewModel
import com.newsroom.app.viewmodels.ArticleViewModelFactory
import kotlinx.android.synthetic.main.fragment_article.*
import javax.inject.Inject

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var articleViewModel: ArticleViewModel
    @Inject
    lateinit var articleViewModelFactory: ArticleViewModelFactory

    val args : ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel = ViewModelProvider(this, articleViewModelFactory).get(ArticleViewModel::class.java)

        val article = args.articleArg

        articleWebView.apply {
            webViewClient = WebViewClient()
            article.url?.let {
                loadUrl(it)
            }
        }

        saveFab.setOnClickListener { view ->
            articleViewModel.saveArticle(article)
            Snackbar.make(view, "Article Saved Successfully!", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as NewsApplication).appComponent.featureComp().create().inject(this)
    }
}