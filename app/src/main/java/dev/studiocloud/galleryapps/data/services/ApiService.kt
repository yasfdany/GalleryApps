package dev.studiocloud.galleryapps.data.services

import dev.studiocloud.galleryapps.data.services.responses.GalleryResponse
import dev.studiocloud.galleryapps.data.services.responses.PlaceResponse
import dev.studiocloud.galleryapps.data.services.responses.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET




interface ApiService {
    @GET("place.json")
    fun getPlaces(): Call<PlaceResponse?>?

    @GET("gallery.json")
    fun getGallery(): Call<GalleryResponse?>?

    @GET("user.json")
    fun getUser(): Call<ProfileResponse?>?
}