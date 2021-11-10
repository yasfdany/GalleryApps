package dev.studiocloud.galleryapps.ui.home.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.studiocloud.galleryapps.data.services.responses.PlaceItem

import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dev.studiocloud.galleryapps.R
import kotlinx.android.synthetic.main.item_place.view.*


interface OnClickPlaceListener{
    fun OnClickPlace(place : PlaceItem)
}

class PlaceAdapter(var context: Context, var data: MutableList<PlaceItem>) : RecyclerView.Adapter<PlaceAdapter.Holder>() {
    var onClickListener : OnClickPlaceListener? = null;

    fun setOnClickPlace(onClickListener : OnClickPlaceListener){
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_place, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = data[position]

        Glide.with(context)
            .load(if(item.type.equals("image")) item.image else item.media?.first())
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(24)))
            .into(holder.itemView.ivPlace)

        holder.itemView.tvTitle.text = item.title
        holder.itemView.tvContent.text = item.content
        holder.itemView.itemParentPlace.setOnClickListener {
            onClickListener?.OnClickPlace(data[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
        }
    }

}