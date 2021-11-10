package site.studiocloud.galleryapps.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_login.*
import site.studiocloud.galleryapps.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail.addTextChangedListener {
            if(it.toString().isValidEmail() && it.toString().isNotEmpty()){
                tvEmailError.visibility = View.GONE ;
            } else {
                tvEmailError.visibility = View.VISIBLE;
            }
        }
    }

    fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}