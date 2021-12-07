package com.luishenrique.cutecatsgallery.ui.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.luishenrique.cutecatsgallery.R
import com.luishenrique.domain.entity.Image
import kotlinx.android.synthetic.main.item_list.view.*

class HomeAdapter(private val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var images: List<Image> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val icon = view.xIcon
        private val username = view.xUsername
        private val score = view.xScore

        fun bind(image: Image) {
            username.text = image.username
            score.text = image.score.toString()


            Glide.with(context)
                .load(image.images?.get(0)?.link)
                .override(200, 240)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_cat_loading)
                .error(R.drawable.ic_cat_loading)
                .into(icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size
}