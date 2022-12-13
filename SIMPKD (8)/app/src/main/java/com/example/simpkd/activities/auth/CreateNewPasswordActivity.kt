package com.example.simpkd.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpkd.R
import com.example.simpkd.apis.forgotPassword
import com.example.simpkd.utils.snackbarWarning
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_new_password.*

class CreateNewPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_password)

        submit_button.setOnClickListener {
            submit_button.submit()

            val email = intent.getStringExtra("EMAIL")
            val password = "${password_text_input_layout.editText!!.text}"
            val confirmPassword = "${confirm_password_text_input_layout.editText!!.text}"

            if (password != confirmPassword) {
                snackbarWarning(root, "Konfirmasi password harus sama dengan password",
                    Snackbar.LENGTH_LONG).show()
                submit_button.submitFinish()
                return@setOnClickListener
            }

            val requestParams = HashMap<String, String>()
            requestParams["email"] = email!!
            requestParams["password"] = password
            requestParams["confirm_password"] = confirmPassword

            forgotPassword(root, requestParams, hashMapOf(), {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, { submit_button.submitFinish() })
        }
    }
}