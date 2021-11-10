package dev.studiocloud.galleryapps.data.services.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceResponse(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("header")
	val header: Header? = null,

	@field:SerializedName("content")
	val content: MutableList<PlaceItem>? = null
) : Parcelable

@Parcelize
data class PlaceItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("media")
	val media: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("content")
	val content: String? = null
) : Parcelable

@Parcelize
data class Header(

	@field:SerializedName("subtitle")
	val subtitle: String? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable
