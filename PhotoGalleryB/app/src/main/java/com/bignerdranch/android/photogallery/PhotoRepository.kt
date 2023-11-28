package com.bignerdranch.android.photogallery

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.GalleryItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class PhotoRepository {
    private val flickrApi: FlickrApi
    private val pagingConfig = PagingConfig(
        pageSize = 100,
        prefetchDistance = 10,
        enablePlaceholders = false
    )

    private val pager = Pager(
        config = pagingConfig,
        pagingSourceFactory = { FlickrPagingSource(flickrApi, apiKey) }
    )

    val pagingFlow: LiveData<PagingData<GalleryItem>> = pager.liveData.cachedIn(viewModelScope)


    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        flickrApi = retrofit.create()
    }

    suspend fun fetchPhotos(): List<GalleryItem> =
        flickrApi.fetchPhotos(1).photos.galleryItems
}
