package com.bignerdranch.android.photogallery

import com.bignerdranch.android.photogallery.api.NasaApi
import com.bignerdranch.android.photogallery.api.NasaResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class NasaRepository {
    private val nasaApi: NasaApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        nasaApi = retrofit.create()
    }

    suspend fun getAstronomyPicturesOfTheDay(count: Int): List<NasaResponse> =
        nasaApi.getAstronomyPictureOfTheDay(count = count)}
