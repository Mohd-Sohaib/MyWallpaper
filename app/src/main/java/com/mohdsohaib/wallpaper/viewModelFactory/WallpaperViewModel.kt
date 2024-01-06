package com.mohdsohaib.wallpaper.viewModelFactory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohdsohaib.wallpaper.MainActivity
import com.mohdsohaib.wallpaper.model.WallpaperResponse
import com.mohdsohaib.wallpaper.repository.WallpaperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class WallpaperViewModel(private val repository: WallpaperRepository) : ViewModel(){

    init {
        getWallpaper("cricket")
    }

    val wallpaper : LiveData<WallpaperResponse>
        get() = repository.wallpaper

    fun getWallpaper(s : String){
        viewModelScope.launch {
            repository.getWallpaper(s)
        }
    }
}