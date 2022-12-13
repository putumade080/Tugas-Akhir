package com.example.simpkd.activities.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpkd.R
import com.example.simpkd.activities.MainActivity
import com.example.simpkd.apis.login
import com.example.simpkd.models.Token
import com.example.simpkd.utils.snackbarError
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var fcmToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful) {
                snackbarError(root, "Gagal terhubung ke server, silahkan buka ulang aplikasi",
                    Snackbar.LENGTH_LONG).show()
                return@addOnCompleteListener
            }
            fcmToken = it.result
        }

        login_button.setOnClickListener {
            login_button.submit()

            val requestParams = HashMap<String, String>()
            requestParams["email"] = "${email_text_input_edit_text.editText!!.text}"
            requestParams["password"] = "${password_text_input_edit_text.editText!!.text}"
            requestParams["platform"] = "Mobile"
            requestParams["notification_token"] = "$fcmToken"

            login(root, requestParams, HashMap(), this:: onSuccessLogin, {login_button.submitFinish()})
        }

        forget_password_material_button.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onSuccessLogin(response: Any?) {
        if (response != null) {
            val token = response as Token
            val sharedPreferences = getSharedPreferences("SESSIONS", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("ACCESS_TOKEN", token.access_token)
            editor.putString("REFRESH_TOKEN", token.refresh_token)
            editor.putString("CHANGE_PASSWORD_STATUS", token.change_password_status.toString())
            editor.apply()

            val intent = Intent(this, if (token.change_password_status)
                MainActivity::class.java else ChangePasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}