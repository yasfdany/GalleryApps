package dev.studiocloud.galleryapps.ui.login

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import dev.studiocloud.galleryapps.R
import dev.studiocloud.galleryapps.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.concurrent.schedule
import android.content.SharedPreferences




class LoginActivity : AppCompatActivity() {
    private lateinit var prefsEditor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefsEditor = getSharedPreferences("GALLERY_APPS", MODE_PRIVATE).edit()

        isButtonEnable(etEmail.text.toString(),etPassword.text.toString())

        etEmail.addTextChangedListener {
            if(it.toString().isValidEmail() && it.toString().isNotEmpty()){
                tvEmailError.visibility = View.GONE
            } else {
                tvEmailError.visibility = View.VISIBLE
            }

            isButtonEnable(it.toString(),etPassword.text.toString())
        }

        etPassword.addTextChangedListener{
            if (it.toString().isValidPassword() && it.toString().length >= 6 && it.toString().isNotEmpty()){
                tvPasswordError.visibility = View.GONE
            } else {
                tvPasswordError.visibility = View.VISIBLE
            }

            isButtonEnable(etEmail.text.toString(),it.toString())
        }

        btLogin.setOnClickListener {
            val progressDialog = createDialog()
            progressDialog.show()
            Timer().schedule(3000){
                prefsEditor.putBoolean("LOGGED",true)
                prefsEditor.apply()
                progressDialog.dismiss()
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
        }
    }

    private fun createDialog(): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setView(R.layout.progress_layout)
        return builder.create()
    }

    private fun isButtonEnable(email : String, password: String){
        btLogin.isEnabled =  email.isValidEmail() && email.isNotEmpty() && password.isValidPassword() && password.length >= 6 && password.isNotEmpty()
        if (btLogin.isEnabled){
            btLogin.setBackgroundColor(resources.getColor(R.color.purple_500))
        } else {
            btLogin.setBackgroundColor(Color.parseColor("#afafaf"))
        }
    }

    private fun String.isValidPassword(): Boolean {
        val pattern: Pattern
        val passwordPattern = "^(?=.*[A-Z]).{4,}\$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(this)
        return matcher.matches()
    }

    private fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}