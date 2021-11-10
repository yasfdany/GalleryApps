package dev.studiocloud.galleryapps.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPaddingDecoration(private var spacing : Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.top = spacing
        } else if (position == state.itemCount - 1) {
            outRect.bottom = spacing
        }
    }
}