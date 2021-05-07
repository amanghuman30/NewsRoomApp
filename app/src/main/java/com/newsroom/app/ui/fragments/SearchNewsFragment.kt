package com.newsroom.app.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsroom.app.R
import com.newsroom.app.adapters.NewsAdapter
import com.newsroom.app.ui.viewmodels.NewsViewModel
import com.newsroom.app.ui.activities.NewsMainActivity
import com.newsroom.app.util.Resource
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private var searchJob : Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as NewsMainActivity).newsViewModel

        setupRecyclerView()

        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                handleQueryTextChange(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handleQueryTextChange(newText)
                return true
            }
        })

        newsViewModel.searchNewsLiveData.observe(viewLifecycleOwner, { resource ->
            when(resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    resource.data?.let { newsApiResponse ->
                        hideProgressBar()
                        newsAdapter.differ.submitList(newsApiResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    resource.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        searchNewsRecycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun handleQueryTextChange(query :String?) {
        query?.let {
            if (query.isNotEmpty()) {
                searchJob?.let {
                    it.cancel()
                }
                searchJob = MainScope().launch {
                    delay(500)
                    newsViewModel.searchNews(query)
                }
            }
        }
    }

    private fun showProgressBar() {
        searchNewsPB.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        searchNewsPB.visibility = View.GONE
    }
}