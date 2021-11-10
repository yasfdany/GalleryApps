package dev.studiocloud.galleryapps.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.data.services.responses.GalleryItem
import kotlinx.android.synthetic.main.item_gallery.view.*


interface OnClickGalleryListener{
    fun onClickGallery(gallery : GalleryItem)
}

class GalleryAdapter(private var context: Context, var data: MutableList<GalleryItem>) : RecyclerView.Adapter<GalleryAdapter.Holder>() {
    private var onClickListener : OnClickGalleryListener? = null

    fun setOnClickGallery(onClickListener : OnClickGalleryListener){
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = data[position]

        Glide.with(context)
            .load(item.image)
            .into(holder.itemView.ivGallery)

        holder.itemView.itemParentGallery.setOnClickListener {
            onClickListener?.onClickGallery(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

}