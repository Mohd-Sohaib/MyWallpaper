package com.mohdsohaib.wallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mohdsohaib.wallpaper.databinding.ActivityWallpaperBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL

class WallpaperActivity : AppCompatActivity() {
    private var _binding : ActivityWallpaperBinding ?= null
    private val binding get() = _binding!!
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = intent.getStringExtra("IMAGE_URL")

        Glide.with(this)
            .load(imageUrl)
            .into(binding.wallpaper)


        binding.setWallpaper.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val bitmap: Bitmap = Picasso.get().load(imageUrl).get()
                val wallpaperManager : WallpaperManager = WallpaperManager.getInstance(this@WallpaperActivity)
                try {
                    wallpaperManager.setBitmap(bitmap)
                    //for run on main Thread
                    this@WallpaperActivity.runOnUiThread(Runnable {
                        Toast.makeText(this@WallpaperActivity, "Wallpaper Set Successfully!", Toast.LENGTH_SHORT).show()
                    })
                }catch (e : IOException){
                    Log.d("SOHAIB","$e")
                }
            }
        }
    }
}