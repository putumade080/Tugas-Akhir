package com.example.simpkd.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.simpkd.R
import com.example.simpkd.activities.auth.ChangePasswordActivity
import com.example.simpkd.activities.auth.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sharedPreferences = getSharedPreferences("SESSIONS", Context.MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            val activity = if (sharedPreferences.contains("CHANGE_PASSWORD_STATUS")) {
                if (sharedPreferences.getString("CHANGE_PASSWORD_STATUS", "").toBoolean()) {
                    MainActivity::class.java
                } else {
                    ChangePasswordActivity::class.java
                }
            } else {
                LoginActivity::class.java
            }

            val intent = Intent(this, activity)
            startActivity(intent)
            finish()}, 3000
        )
    }
}