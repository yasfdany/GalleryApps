package dev.studiocloud.galleryapps.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.data.viewModels.PlaceViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import dev.studiocloud.galleryapps.data.services.responses.PlaceItem
import dev.studiocloud.galleryapps.ui.home.adapters.OnClickPlaceListener
import dev.studiocloud.galleryapps.ui.home.adapters.PlaceAdapter
import dev.studiocloud.galleryapps.utils.RecyclerViewPaddingDecoration
import kotlinx.android.synthetic.main.place_fragment.*
import kotlinx.android.synthetic.main.place_fragment.view.*


class PlaceFragment : Fragment() {
    private lateinit var placeAdapter: PlaceAdapter
    private lateinit var placeViewModel : PlaceViewModel
    private val places = mutableListOf<PlaceItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.place_fragment,container,false)

        placeAdapter = PlaceAdapter(requireContext(),places);
        view.rvPlace.layoutManager = LinearLayoutManager(requireContext())
        view.rvPlace.adapter = placeAdapter
        view.rvPlace.addItemDecoration(RecyclerViewPaddingDecoration(56))

        placeAdapter.setOnClickPlace(
            object : OnClickPlaceListener {
                override fun onClickPlace(place: PlaceItem) {

                }
            }
        )

        placeViewModel = ViewModelProvider(requireActivity())[PlaceViewModel::class.java]
        placeViewModel.searchResult.observe(viewLifecycleOwner, {
            if(it != null){
                places.clear()
                places.addAll(it)
                placeAdapter.notifyDataSetChanged()
            }
        })

        view.etSearchPlace.addTextChangedListener {
            placeViewModel.getPlaces(if (it.toString().isNotEmpty()) it.toString() else null)
        }

        return view
    }
}