package com.app.appnews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(var context: Context, var articles: List<Article>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>(){

    var t =1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {

            val inflater = LayoutInflater.from(context)
            var view = inflater.inflate(R.layout.item_view, parent, false)
            return MyViewHolder(view)

    }



    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDesc.text = article.description

        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, article.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("url", article.url)
            context.startActivity(intent)
        }
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var newsTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var newsDesc = itemView.findViewById<TextView>(R.id.tvDesc)
        var newsImage = itemView.findViewById<ImageView>(R.id.image)

    }



}