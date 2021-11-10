package dev.studiocloud.galleryapps.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.navigation.NavigationBarView
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.ui.home.fragments.GalleryFragment
import dev.studiocloud.galleryapps.ui.home.fragments.PlaceFragment
import dev.studiocloud.galleryapps.ui.home.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val pages = listOf(
        PlaceFragment(),
        GalleryFragment(),
        ProfileFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        vpHome.adapter = MainFragmentAdapter(supportFragmentManager,pages)
        vpHome.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        bottomNavMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.place_nav -> {
                    vpHome.setCurrentItem(0,true)
                    return@setOnItemSelectedListener true
                }
                R.id.gallery_nav -> {
                    vpHome.setCurrentItem(1,true)
                    return@setOnItemSelectedListener true
                }
                R.id.profile_nav -> {
                    vpHome.setCurrentItem(2,true)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
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