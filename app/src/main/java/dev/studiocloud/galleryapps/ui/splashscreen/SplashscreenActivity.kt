package dev.studiocloud.galleryapps.ui.splashscreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*
import java.util.*
import kotlin.concurrent.schedule
import android.content.SharedPreferences
import dev.studiocloud.galleryapps.ui.home.HomeActivity


class SplashscreenActivity : AppCompatActivity() {
    private lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = getSharedPreferences("GALLERY_APPS", MODE_PRIVATE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setContentView(R.layout.activity_splashscreen)

        Timer().schedule(1000){
            if (prefs.getBoolean("LOGGED",false)){
                startActivity(Intent(this@SplashscreenActivity, HomeActivity::class.java))
            } else {
                startActivity(Intent(this@SplashscreenActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}