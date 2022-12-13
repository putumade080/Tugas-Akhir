package com.example.simpkd.apis

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.HttpHeaderParser
import com.example.simpkd.activities.auth.LoginActivity
import com.example.simpkd.models.Token
import com.example.simpkd.services.FileDataPart
import com.example.simpkd.services.VolleyMultipartRequest
import com.example.simpkd.utils.Constants
import com.example.simpkd.utils.snackbarError
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.json.JSONObject

fun refreshToken(contextView: View,
                 requestParams: HashMap<String, String>,
                 fileRequestParams: HashMap<String, FileDataPart>,
                 successCallback: (Any?) -> Unit,
                 errorCallback: () -> Unit,
                 callback: (
                     View, HashMap<String, String>, HashMap<String, FileDataPart>, (Any?) -> Unit,
                     () -> Unit, Boolean) -> Unit,
                 showMessage: Boolean = true) {
    val sharedPreferences = contextView.context.getSharedPreferences("SESSIONS", Context.MODE_PRIVATE)
    val refreshToken = sharedPreferences.getString("REFRESH_TOKEN", null)
    val onRefresh = sharedPreferences.getBoolean("ON_REFRESH", false)

    if (onRefresh) {
        callback(contextView, requestParams, fileRequestParams, successCallback, errorCallback,
            showMessage)
        return
    }
    if (refreshToken != null) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("ON_REFRESH", true)
        if (!editor.commit()) return

        val headers = HashMap<String, String>()
        headers["x-auth-token"] = "Bearer $refreshToken"

        VolleyMultipartRequest(contextView.context, Request.Method.POST,
            "${Constants.BASE_URL}/token/refresh/", headers, HashMap(), HashMap(), {
                val json = String(it.data, charset(HttpHeaderParser.parseCharset(it.headers)))
                val responseObject = JSONObject(json)

                editor.putBoolean("ON_REFRESH", false)
                editor.apply()

                when (responseObject.getInt("status_code")) {
                    200 -> {
                        val token: Token = Gson().fromJson(responseObject.getString("data"),
                            Token::class.java)
                        editor.putString("ACCESS_TOKEN", token.access_token)
                        editor.putString("REFRESH_TOKEN", token.refresh_token)
                        if (editor.commit()) callback(contextView, requestParams, fileRequestParams,
                            successCallback, errorCallback, showMessage)
                    }
                    401 -> {
                        editor.remove("ACCESS_TOKEN")
                        editor.remove("REFRESH_TOKEN")
                        editor.apply()

                        val intent = Intent(contextView.context, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        contextView.context.startActivity(intent)
                    }
                    else -> {
                        snackbarError(contextView, responseObject.getString("message"),
                            Snackbar.LENGTH_LONG).show()
                    }
                }
            }, {
                editor.putBoolean("ON_REFRESH", false)
                editor.apply()
                Log.e("ERROR", it.message!!)
            }).start()

        return
    }

    val intent = Intent(contextView.context, LoginActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    contextView.context.startActivity(intent)
}