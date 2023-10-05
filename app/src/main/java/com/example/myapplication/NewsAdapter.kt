package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.NewsListItemBinding

class NewsAdapter(val a: Activity, val articles: ArrayList<Article>): RecyclerView.Adapter<NewsAdapter.NewsVH>() {
    class NewsVH(val binding: NewsListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val binding = NewsListItemBinding.inflate(a.layoutInflater, parent,false)
        return NewsVH(binding)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.binding.newsTitle.text = articles[position].title
        Glide
            .with(a)
            .load(articles[position].urlToImage)
            .error(R.drawable.broken_image)
            .into(holder.binding.newsImage)
        holder.binding.newsCard.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, articles[position].url.toUri())
            a.startActivity(i)
        }
        holder.binding.share.setOnClickListener {
            ShareCompat
                .IntentBuilder(a)
                .setType("text/plain")
                .setChooserTitle("Share Article With:")
                .setText(articles[position].url)
                .startChooser()
        }
    }
}