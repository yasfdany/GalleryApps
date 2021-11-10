package dev.studiocloud.galleryapps.data.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.studiocloud.galleryapps.data.services.ApiClient
import dev.studiocloud.galleryapps.data.services.ApiService
import dev.studiocloud.galleryapps.data.services.responses.ProfileResponse
import dev.studiocloud.galleryapps.data.services.responses.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel : ViewModel() {
    val userData : MutableLiveData<User?> = MutableLiveData()

    fun getUserData(){
        val client: ApiService? = ApiClient().get()

        client?.getUser()?.enqueue(
            object : Callback<ProfileResponse?>{
                override fun onResponse(
                    call: Call<ProfileResponse?>,
                    response: Response<ProfileResponse?>
                ) {
                    if (response.code() == 200){
                        userData.value = response.body()?.data
                    }
                }

                override fun onFailure(call: Call<ProfileResponse?>, t: Throwable) {
                }
            }
        )
    }
}