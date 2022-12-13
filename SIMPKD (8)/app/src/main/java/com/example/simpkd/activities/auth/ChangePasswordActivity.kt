package com.example.simpkd.activities.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpkd.R
import com.example.simpkd.activities.MainActivity
import com.example.simpkd.apis.updatePassword
import com.example.simpkd.utils.snackbarWarning
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_change_password.root
import kotlinx.android.synthetic.main.activity_form_vehicle_maintenance.*

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        submit_button.setOnClickListener {
            submit_button.submit()

            val password = "${password_text_input_layout.editText!!.text}"
            val confirmPassword = "${confirm_password_text_input_layout.editText!!.text}"

            if (password != confirmPassword) {
                snackbarWarning(root, "Konfirmasi password harus sama dengan password",
                    Snackbar.LENGTH_LONG).show()
                submit_button.submitFinish()
                return@setOnClickListener
            }

            val requestParams = HashMap<String, String>()
            requestParams["password"] = password
            requestParams["confirm_password"] = confirmPassword

            updatePassword(root, requestParams, hashMapOf(), {
                val sharedPreferences = getSharedPreferences("SESSIONS", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("CHANGE_PASSWORD_STATUS", true.toString())
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, { submit_button.submitFinish() })
        }
    }
}