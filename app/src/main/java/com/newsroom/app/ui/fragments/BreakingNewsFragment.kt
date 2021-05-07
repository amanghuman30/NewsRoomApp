package com.newsroom.app.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsroom.app.R
import com.newsroom.app.adapters.NewsAdapter
import com.newsroom.app.ui.viewmodels.NewsViewModel
import com.newsroom.app.ui.activities.NewsMainActivity
import com.newsroom.app.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter : NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as NewsMainActivity).newsViewModel

        setupRecyclerView()

        newsViewModel.breakingNewsLiveData.observe(viewLifecycleOwner, { resource ->
            when(resource){
                is Resource.Success -> {
                    hideProgressBar()
                    resource.data?.let { response ->
                        newsAdapter.differ.submitList(response.articles)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    resource.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        breakingNewsRecycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        breakingNewsPB.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        breakingNewsPB.visibility = View.GONE
    }

}