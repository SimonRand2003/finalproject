package com.bignerdranch.android.photogallery

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.GalleryItem

class FlickrPagingSource(
    private val flickrApi: FlickrApi,
) : PagingSource<Int, GalleryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        try {
            val page = params.key ?: 1 // Initial page if null
            val response = flickrApi.fetchPhotos(page)
            val photos = response.photos.galleryItems

            // Return the loaded data with next and/or previous page keys
            return LoadResult.Page(
                data = photos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (photos.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        TODO("Not yet implemented")
    }
}

