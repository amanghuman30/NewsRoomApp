package com.newsroom.app.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsroom.app.R
import com.newsroom.app.adapters.NewsAdapter
import com.newsroom.app.viewmodels.NewsViewModel
import com.newsroom.app.ui.activities.NewsMainActivity
import com.newsroom.app.util.Constants
import com.newsroom.app.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter : NewsAdapter
    var isLoading = false
    var isScrolling = false
    var isLastPage = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as NewsMainActivity).newsViewModel

        setupRecyclerView()

        newsViewModel.breakingNewsLiveData.observe(viewLifecycleOwner, { resource ->
            when(resource){
                is Resource.Success -> {
                    hideProgressBar()
                    resource.data?.let { response ->
                        newsAdapter.differ.submitList(response.articles.toList())
                        val totalPages = response.totalResults / Constants.QUERY_ITEM_COUNT +2
                        isLastPage = newsViewModel.breakingNewsPageNumber == totalPages
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

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling = true
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val totalItemsVisibleCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage

            val isAtLastItem = firstVisibleItemPosition+totalItemsVisibleCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_ITEM_COUNT

            val shouldDoPagination = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning
                    && isTotalMoreThanVisible && isScrolling

            if(shouldDoPagination) {
                newsViewModel.getBreakingNews("us")
                isScrolling = false
            }



        }

    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        breakingNewsRecycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putSerializable("article_arg", it)
            }
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment, bundle)
        }
    }

    private fun showProgressBar() {
        breakingNewsPB.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideProgressBar() {
        breakingNewsPB.visibility = View.GONE
        isLoading = false
    }

}