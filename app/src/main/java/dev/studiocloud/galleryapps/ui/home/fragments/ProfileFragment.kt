package dev.studiocloud.galleryapps.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.data.viewModels.UserViewModel
import kotlinx.android.synthetic.main.profile_fragment.view.*

class ProfileFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.profile_fragment,container,false)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        userViewModel.userData.observe(viewLifecycleOwner,{
            Glide.with(requireActivity())
                .load(it?.avatar)
                .into(view.ivProfile)
            view.tvFullname.text = it?.fullname
            view.tvEmail.text = it?.email
            view.tvUsername.text = it?.username
            view.tvPhone.text = it?.phone
        })

        return view
    }
}