package com.example.simpkd.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.simpkd.R
import com.example.simpkd.apis.validateForgotPassword
import kotlinx.android.synthetic.main.activity_forget_password_otp.*

class ForgetPasswordOTPActivity : AppCompatActivity() {
    lateinit var email: String
    private val otp = mutableListOf("", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password_otp)

        email = intent.getStringExtra("EMAIL")!!
        submit_button.isEnabled = false

        otp_1.editText!!.addTextChangedListener {
            otp[0] = "$it"

            if (otp.filter { v -> v.isNotBlank() }.size == otp.size) {
                onValidateRequest(true)
            } else {
                onValidateRequest(false)
                if ("$it".isNotBlank()) otp_2.editText!!.requestFocus()
            }
        }

        otp_2.editText!!.addTextChangedListener {
            otp[1] = "$it"

            if (otp.filter { v -> v.isNotBlank() }.size == otp.size) {
                onValidateRequest(true)
            } else {
                onValidateRequest(false)
                if ("$it".isNotBlank()) otp_3.editText!!.requestFocus()
            }
        }

        otp_3.editText!!.addTextChangedListener {
            otp[2] = "$it"

            if (otp.filter { v -> v.isNotBlank() }.size == otp.size) {
                onValidateRequest(true)
            } else {
                onValidateRequest(false)
                if ("$it".isNotBlank()) otp_4.editText!!.requestFocus()
            }
        }

        otp_4.editText!!.addTextChangedListener {
            otp[3] = "$it"

            if (otp.filter { v -> v.isNotBlank() }.size == otp.size) {
                onValidateRequest(true)
            } else {
                onValidateRequest(false)
                if ("$it".isNotBlank()) otp_5.editText!!.requestFocus()
            }
        }

        otp_5.editText!!.addTextChangedListener {
            otp[4] = "$it"

            if (otp.filter { v -> v.isNotBlank() }.size == otp.size) {
                onValidateRequest(true)
            } else {
                onValidateRequest(false)
            }
        }

        submit_button.setOnClickListener {
            onValidateRequest(true)
        }
    }

    private fun onValidateRequest(status: Boolean) {
        submit_button.isEnabled = status
        otp_1.editText!!.isEnabled = !status
        otp_2.editText!!.isEnabled = !status
        otp_3.editText!!.isEnabled = !status
        otp_4.editText!!.isEnabled = !status
        otp_5.editText!!.isEnabled = !status

        if (!status) return

        submit_button.submit()
        val requestParams = HashMap<String, String>()
        requestParams["email"] = email
        requestParams["otp"] = otp.joinToString(separator = "")

        validateForgotPassword(root, requestParams, hashMapOf(), this::onSuccessValidate, {
            submit_button.submitFinish()
            onValidateRequest(false)
            submit_button.isEnabled = true
        })
    }

    private fun onSuccessValidate(response: Any?) {
        val intent = Intent(this, CreateNewPasswordActivity::class.java)
        intent.putExtra("EMAIL", email)
        startActivity(intent)
        finish()
    }
}