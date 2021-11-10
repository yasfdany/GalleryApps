package dev.studiocloud.galleryapps.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.data.services.responses.GalleryItem
import dev.studiocloud.galleryapps.data.viewModels.GalleryViewModel
import dev.studiocloud.galleryapps.ui.home.adapters.GalleryAdapter
import dev.studiocloud.galleryapps.ui.home.adapters.OnClickGalleryListener
import kotlinx.android.synthetic.main.gallery_fragment.view.*

class GalleryFragment : Fragment() {
    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var galleryViewModel : GalleryViewModel
    private val galleries = mutableListOf<GalleryItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.gallery_fragment,container,false)

        galleryAdapter = GalleryAdapter(requireContext(),galleries)
        view.rvGallery.layoutManager = GridLayoutManager(requireContext(), 3)
        view.rvGallery.adapter = galleryAdapter

        galleryAdapter.setOnClickGallery(object : OnClickGalleryListener{
            override fun onClickGallery(gallery: GalleryItem) {
            }
        })

        galleryViewModel = ViewModelProvider(requireActivity())[GalleryViewModel::class.java]
        galleryViewModel.searchResult.observe(viewLifecycleOwner, {
            if (it != null){
                galleries.clear()
                galleries.addAll(it)
                galleryAdapter.notifyDataSetChanged()

                view.viewGalleryNotFound.visibility = if(galleries.isEmpty()) View.VISIBLE else View.GONE
            }
        })

        view.etSearchGallery.addTextChangedListener {
            galleryViewModel.getGallery(if (it.toString().isNotEmpty()) it.toString() else null)
        }

        return view
    }
}