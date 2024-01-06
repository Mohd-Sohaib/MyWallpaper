package com.mohdsohaib.wallpaper.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohdsohaib.wallpaper.api.ApiService
import com.mohdsohaib.wallpaper.model.WallpaperResponse

class WallpaperRepository(private val wallpaperService : ApiService) {

    private val wallpaperLiveData = MutableLiveData<WallpaperResponse>()

    val wallpaper : LiveData<WallpaperResponse>
        get() = wallpaperLiveData

    suspend fun getWallpaper(query: String) {
        val result = wallpaperService.getWallpaper(query)
        if(result.body() != null){
            wallpaperLiveData.postValue(result.body())
        }
    }
}