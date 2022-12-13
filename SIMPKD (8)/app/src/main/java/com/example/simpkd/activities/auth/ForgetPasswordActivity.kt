package com.example.simpkd.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpkd.R
import com.example.simpkd.apis.requestForgotPassword
import com.example.simpkd.utils.snackbarWarning
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        submit_button.setOnClickListener {
            submit_button.submit()
            val email = "${email_text_input_layout.editText!!.text}"

            if (email.isBlank()) {
                snackbarWarning(root, "Email tidak boleh kosong", Snackbar.LENGTH_LONG)
                    .show()
                submit_button.submitFinish()
                return@setOnClickListener
            }

            val requestParams = HashMap<String, String>()
            requestParams["email"] = email

            requestForgotPassword(root, requestParams, hashMapOf(), this::onRequestSuccess, {
                submit_button.submitFinish()
            })
        }
    }

    private fun onRequestSuccess(response: Any?) {
        submit_button.submitFinish()
        val intent = Intent(this, ForgetPasswordOTPActivity::class.java)
        intent.putExtra("EMAIL", "${email_text_input_layout.editText!!.text}")
        startActivity(intent)
        finish()
    }
}