package dev.studiocloud.galleryapps.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.data.services.responses.PlaceItem
import kotlinx.android.synthetic.main.item_place.view.*


interface OnClickPlaceListener{
    fun onClickPlace(place : PlaceItem)
}

class PlaceAdapter(private var context: Context, var data: MutableList<PlaceItem>) : RecyclerView.Adapter<PlaceAdapter.Holder>() {
    private var onClickListener : OnClickPlaceListener? = null

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
            .into(holder.itemView.ivPlace)

        holder.itemView.tvTitle.text = item.title
        holder.itemView.tvContent.text = item.content
        holder.itemView.itemParentPlace.setOnClickListener {
            onClickListener?.onClickPlace(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

}