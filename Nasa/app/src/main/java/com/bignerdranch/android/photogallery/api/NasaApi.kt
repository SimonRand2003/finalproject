package com.bignerdranch.android.photogallery.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val NASA_API_KEY = "wF3tgy7P4JcLHqBzyL0azqOPkc12KT7KfycQdgDk"

interface NasaApi {
    @GET("planetary/apod")
    suspend fun getAstronomyPictureOfTheDay(
        @Query("api_key") apiKey: String = NASA_API_KEY,
        @Query("count") count: Int
    ): List<NasaResponse>
}
