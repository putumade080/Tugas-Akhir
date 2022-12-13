package com.example.simpkd.apis

import android.util.Log
import android.view.View
import com.android.volley.Request
import com.example.simpkd.models.Token
import com.example.simpkd.services.FileDataPart
import com.example.simpkd.services.VolleyMultipartRequest
import com.example.simpkd.utils.Constants
import com.example.simpkd.utils.getResponseData
import com.google.gson.Gson

fun login(contextView: View, requestParams: HashMap<String, String>,
          fileRequestParams: HashMap<String, FileDataPart>, callback: (Any?) -> Unit,
          errorCallback: () -> Unit, showMessage: Boolean = true) {
    VolleyMultipartRequest(contextView.context, Request.Method.POST,
        "${Constants.BASE_URL}/pegawai/login/", HashMap(), requestParams,
        fileRequestParams, {
            val dataString = getResponseData(it, contextView, requestParams,
                fileRequestParams, callback, errorCallback, ::login, showMessage)

            if (dataString != null) {
                val token = Gson().fromJson(dataString, Token::class.java)
                callback(token)
            }
        }, { errorCallback() }).start()
}