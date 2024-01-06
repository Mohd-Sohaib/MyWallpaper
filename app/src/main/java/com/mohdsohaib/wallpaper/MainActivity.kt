package com.mohdsohaib.wallpaper

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mohdsohaib.wallpaper.adapter.WallpaperAdapter
import com.mohdsohaib.wallpaper.api.ApiService
import com.mohdsohaib.wallpaper.api.RetrofitInstance
import com.mohdsohaib.wallpaper.databinding.ActivityMainBinding
import com.mohdsohaib.wallpaper.model.Photo
import com.mohdsohaib.wallpaper.repository.WallpaperRepository
import com.mohdsohaib.wallpaper.viewModelFactory.WallpaperViewModel
import com.mohdsohaib.wallpaper.viewModelFactory.WallpaperViewModelFactory

class MainActivity() : AppCompatActivity(), WallpaperAdapter.OnItemClickListener {

    private lateinit var viewModel: WallpaperViewModel
    private lateinit var wallpaperadapter: WallpaperAdapter
    private var _binding: ActivityMainBinding ?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wallpaperService = RetrofitInstance.getInstance().create(ApiService::class.java)
        val repository = WallpaperRepository(wallpaperService)
        viewModel = ViewModelProvider(this,WallpaperViewModelFactory(repository))[WallpaperViewModel::class.java]

        setUpRecyclerView()


        if(isInternetAvailable(this)){
            viewModel.wallpaper.observe(this, Observer {
                val response = it.photos
                wallpaperadapter.setWallpaper(response as ArrayList<Photo>,this)
            })

        }else{
            Toast.makeText(this,"Internet Connection Not Available!", Toast.LENGTH_SHORT).show()
        }


        binding.searchIcon?.setOnClickListener {
           val searchText = binding.searchText?.text
            viewModel.getWallpaper(searchText.toString())
        }

        binding.landscapeText?.setOnClickListener {
            viewModel.getWallpaper(binding.landscapeText!!.text.toString())
            binding.searchText?.text?.clear()
            Toast.makeText(this,"${binding.landscapeText!!.text}",Toast.LENGTH_SHORT).show()
           // binding.landscape?.setBackgroundColor(ContextCompat.getColor(this,R.color.selected))
        }

        binding.cricketText?.setOnClickListener {
            viewModel.getWallpaper(binding.cricketText!!.text.toString())
            binding.searchText?.text?.clear()
            Toast.makeText(this,"${binding.cricketText!!.text}",Toast.LENGTH_SHORT).show()
            // binding.cricket?.setBackgroundColor(ContextCompat.getColor(this,R.color.selected))
        }

        binding.workoutText?.setOnClickListener {
            viewModel.getWallpaper(binding.workoutText!!.text.toString())
            binding.searchText?.text?.clear()
            Toast.makeText(this,"${binding.workoutText!!.text}",Toast.LENGTH_SHORT).show()
            //  binding.workout?.setBackgroundColor(ContextCompat.getColor(this,R.color.selected))
        }

        binding.studentsText?.setOnClickListener {
            viewModel.getWallpaper(binding.studentsText!!.text.toString())
            binding.searchText?.text?.clear()
            Toast.makeText(this,"${binding.studentsText!!.text}",Toast.LENGTH_SHORT).show()
            //binding.students?.setBackgroundColor(ContextCompat.getColor(this,R.color.selected))
        }

        binding.healthcareText?.setOnClickListener {
            viewModel.getWallpaper(binding.healthcareText!!.text.toString())
            binding.searchText?.text?.clear()
            Toast.makeText(this,"${binding.healthcareText!!.text}",Toast.LENGTH_SHORT).show()
            // binding.healthcare?.setBackgroundColor(ContextCompat.getColor(this,R.color.selected))
        }
    }

    private fun setUpRecyclerView() {
        wallpaperadapter = WallpaperAdapter(this)
        binding.wallpaperRecyclerView?.apply {
            adapter = wallpaperadapter
            layoutManager = GridLayoutManager(this@MainActivity,2)

        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    override fun OnItemClick(imageUrl: String) {
         val intent = Intent(this, WallpaperActivity::class.java)
        intent.putExtra("IMAGE_URL", imageUrl)
        startActivity(intent)
    }
}