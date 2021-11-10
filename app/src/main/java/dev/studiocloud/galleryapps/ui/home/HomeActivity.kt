package dev.studiocloud.galleryapps.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.navigation.NavigationBarView
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.data.viewModels.GalleryViewModel
import dev.studiocloud.galleryapps.data.viewModels.PlaceViewModel
import dev.studiocloud.galleryapps.ui.home.fragments.GalleryFragment
import dev.studiocloud.galleryapps.ui.home.fragments.PlaceFragment
import dev.studiocloud.galleryapps.ui.home.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var placeViewModel : PlaceViewModel
    private lateinit var galleryViewModel : GalleryViewModel
    private val pages = listOf(
        PlaceFragment(),
        GalleryFragment(),
        ProfileFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        placeViewModel = ViewModelProvider(this)[PlaceViewModel::class.java]
        galleryViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]

        placeViewModel.getPlaces(null)
        galleryViewModel.getGalleries(null)

        bottomNavMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.place_nav -> {
                    tvPageTitle.text = "Place"
                    vpHome.setCurrentItem(0,true)
                    return@setOnItemSelectedListener true
                }
                R.id.gallery_nav -> {
                    tvPageTitle.text = "Gallery"
                    vpHome.setCurrentItem(1,true)
                    return@setOnItemSelectedListener true
                }
                R.id.profile_nav -> {
                    tvPageTitle.text = "User"
                    vpHome.setCurrentItem(2,true)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        vpHome.adapter = MainFragmentAdapter(supportFragmentManager,pages)
        vpHome.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavMain.menu.getItem(position).isChecked = true;
                when(position){
                    0 -> tvPageTitle.text = "Place"
                    1 -> tvPageTitle.text = "Gallery"
                    2 -> tvPageTitle.text = "User"
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }
}

class MainFragmentAdapter(
    fm: FragmentManager,
    private val fragments: List<Fragment>,
    ): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

}