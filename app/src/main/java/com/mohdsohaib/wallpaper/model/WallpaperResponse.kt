package com.mohdsohaib.wallpaper.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class WallpaperResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)