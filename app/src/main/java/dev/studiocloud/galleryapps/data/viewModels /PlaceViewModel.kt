package dev.studiocloud.galleryapps.data.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.studiocloud.galleryapps.data.services.responses.PlaceItem
import dev.studiocloud.galleryapps.data.services.ApiClient

import dev.studiocloud.galleryapps.data.services.ApiService
import dev.studiocloud.galleryapps.data.services.responses.PlaceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaceViewModel : ViewModel() {
    val placeData : MutableLiveData<MutableList<PlaceItem>?> = MutableLiveData()
    val searchResult : MutableLiveData<MutableList<PlaceItem>?> = MutableLiveData()

    fun getPlaces(searchQuery: String?): MutableLiveData<MutableList<PlaceItem>?> {
        getNetworkPlaces(searchQuery);
        return searchResult
    }

    private fun searchPlace(searchQuery: String?){
        val places: MutableList<PlaceItem> = mutableListOf()

        if(searchQuery != null){
            for (item : PlaceItem in placeData.value!!){
                if (item.title?.contains(searchQuery,true) == true){
                    places.add(item)
                }
            }
            searchResult.value = places
        } else {
            searchResult.value = placeData.value
        }
    }

    private fun getNetworkPlaces(searchQuery: String?){
        val places: MutableList<PlaceItem> = mutableListOf()
        val client: ApiService? = ApiClient().get()

        if (placeData.value != null){
            searchPlace(searchQuery)
        } else {
            client?.getPlaces()?.enqueue(
                object : Callback<PlaceResponse?>{
                    override fun onResponse(
                        call: Call<PlaceResponse?>,
                        response: Response<PlaceResponse?>
                    ) {
                        if (response.code() == 200){
                            places.addAll(response.body()?.data?.content!!)
                            placeData.value = places
                            searchPlace(searchQuery)
                        }
                    }

                    override fun onFailure(call: Call<PlaceResponse?>, t: Throwable) {
                    }
                }
            )
        }
    }
}