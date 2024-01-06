package com.mohdsohaib.wallpaper.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohdsohaib.wallpaper.repository.WallpaperRepository

//ViewModelFactory use for making objects.
@Suppress("UNCHECKED_CAST")
class WallpaperViewModelFactory(private val wallpaperRepository: WallpaperRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WallpaperViewModel(wallpaperRepository) as T
    }
}