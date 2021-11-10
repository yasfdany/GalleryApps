package dev.studiocloud.galleryapps.data.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.studiocloud.galleryapps.data.services.ApiClient

import dev.studiocloud.galleryapps.data.services.ApiService
import dev.studiocloud.galleryapps.data.services.responses.GalleryItem
import dev.studiocloud.galleryapps.data.services.responses.GalleryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GalleryViewModel : ViewModel() {
    private val galleryData : MutableLiveData<MutableList<GalleryItem>?> = MutableLiveData()
    val searchResult : MutableLiveData<MutableList<GalleryItem>?> = MutableLiveData()

    fun getGalleries(searchQuery: String?): MutableLiveData<MutableList<GalleryItem>?> {
        getNetworkPlaces(searchQuery);
        return searchResult
    }

    private fun searchGallery(searchQuery: String?){
        val places: MutableList<GalleryItem> = mutableListOf()

        if(searchQuery != null){
            for (item : GalleryItem in galleryData.value!!){
                if (item?.caption?.contains(searchQuery,true) == true){
                    places.add(item)
                }
            }
            searchResult.value = places
        } else {
            searchResult.value = galleryData.value
        }
    }

    private fun getNetworkPlaces(searchQuery: String?){
        val galleries: MutableList<GalleryItem> = mutableListOf()
        val client: ApiService? = ApiClient().get()

        if (galleryData.value != null){
            searchGallery(searchQuery)
        } else {
            client?.getGallery()?.enqueue(
                object : Callback<GalleryResponse?>{
                    override fun onResponse(
                        call: Call<GalleryResponse?>,
                        response: Response<GalleryResponse?>
                    ) {
                        if (response.code() == 200){
                            galleries.addAll(response.body()?.data!!)
                            galleryData.value = galleries
                            searchGallery(searchQuery)
                        }
                    }

                    override fun onFailure(call: Call<GalleryResponse?>, t: Throwable) {
                    }
                }
            )
        }
    }
}