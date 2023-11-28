package com.bignerdranch.android.photogallery.api

import retrofit2.http.GET

private const val API_KEY = "4ca7f3b3d90e9a57c8780ae879a24e71"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
            "&api_key=$API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}
