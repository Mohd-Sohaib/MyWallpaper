package com.mohdsohaib.wallpaper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohdsohaib.wallpaper.MainActivity
import com.mohdsohaib.wallpaper.WallpaperActivity
import com.mohdsohaib.wallpaper.databinding.ItemWallpaperBinding
import com.mohdsohaib.wallpaper.model.Photo

class WallpaperAdapter(private val listener : OnItemClickListener) : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

    private var list = ArrayList<Photo>()
    private lateinit var context : Context

    @SuppressLint("NotifyDataSetChanged")
    fun setWallpaper(list: ArrayList<Photo>, context: Context){
        this.list = list
        this.context = context
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(ItemWallpaperBinding
            .inflate(LayoutInflater
                .from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {

        val currentPosition = list[position]
        Glide.with(holder.itemView)
            .load(list[position].src.portrait)
            .into(holder.binding.wallpaperView)

        holder.itemView.setOnClickListener {
            listener.OnItemClick(currentPosition.src.portrait)
        }

    }

    class WallpaperViewHolder(val binding: ItemWallpaperBinding) : RecyclerView.ViewHolder(binding.root){}

    interface OnItemClickListener{
        fun OnItemClick(imageUrl: String)
    }
}