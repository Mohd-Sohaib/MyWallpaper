package com.mohdsohaib.wallpaper.api

import com.mohdsohaib.wallpaper.utils.Constants
import com.mohdsohaib.wallpaper.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {    //RetrofitHelper
//    companion object{
//        private val retrofit by lazy {
//            Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//
//        val api by lazy {
//            retrofit.create(ApiService::class.java)
//        }
//    }

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}