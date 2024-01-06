package com.mohdsohaib.wallpaper.api

import com.mohdsohaib.wallpaper.model.WallpaperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: UW2oJMyanyHQk7K3NbJeqxAKsIZ5WTpDD5xPfnABU5ZoOGURa2Eom9By")
    @GET("search")
    suspend fun getWallpaper(
        @Query("query") query: String,
        @Query("per_page") perpage : Int = 80
    ) : Response<WallpaperResponse>
}