package com.bignerdranch.android.photogallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.photogallery.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class NasaGalleryViewModel : ViewModel() {
    private val nasaRepository = NasaRepository()

    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val nasaResponses = nasaRepository.getAstronomyPicturesOfTheDay(count = 50)
                val items = nasaResponses.mapIndexed { index, nasaResponse ->
                    GalleryItem(
                        title = "NASA Astronomy Picture of the Day $index",
                        id = "apod_$index",
                        url = nasaResponse.imageUrl
                    )
                }
                Log.d(TAG, "Items received: $items")
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }
}
