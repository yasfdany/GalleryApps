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
import kotlin.concurrent.schedule

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
            isButtonEnable(etEmail.text.toString(),it.toString())
        }

        btLogin.setOnClickListener {
            val progressDialog = createDialog()
            progressDialog.show()
            Timer().schedule(3000){
                progressDialog.dismiss()
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            }
        }
    }

    private fun createDialog(): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setView(R.layout.progress_layout)
        return builder.create()
    }

    private fun isButtonEnable(email : String, password: String){
        btLogin.isEnabled =  email.isValidEmail() && email.isNotEmpty() && password.isNotEmpty()
        if (btLogin.isEnabled){
            btLogin.setBackgroundColor(resources.getColor(R.color.purple_500))
        } else {
            btLogin.setBackgroundColor(Color.parseColor("#afafaf"))
        }
    }

    private fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}