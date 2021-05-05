package com.newsroom.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsroom.app.R
import com.newsroom.app.ui.models.Article
import kotlinx.android.synthetic.main.item_view_article.view.*

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {


    private var differItemCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differItemCallback)

    inner class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_article, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.url).into(imageViewArticle)
            titleArticleTV.text = article.title
            descriptionArticleTV.text = article.description
            sourceArticleTV.text = article.source.name
            postDateTV.text = article.publishedAt

            setOnClickListener {
                itemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var itemClickListener : ((Article) -> Unit)? = null

    fun setOnClickListener(listener : (Article) -> Unit) {
        itemClickListener = listener
    }


}