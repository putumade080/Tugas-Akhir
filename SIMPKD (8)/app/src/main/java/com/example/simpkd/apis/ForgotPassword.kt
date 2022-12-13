package com.example.simpkd.apis

import android.view.View
import com.android.volley.Request
import com.example.simpkd.services.FileDataPart
import com.example.simpkd.services.VolleyMultipartRequest
import com.example.simpkd.utils.Constants
import com.example.simpkd.utils.getResponseData

fun forgotPassword(contextView: View, requestParams: HashMap<String, String>,
                   fileRequestParams: HashMap<String, FileDataPart>, callback: (Any?) -> Unit,
                   errorCallback: () -> Unit, showMessage: Boolean = true) {
    VolleyMultipartRequest(contextView.context, Request.Method.POST,
        "${Constants.BASE_URL}/pegawai/forgot-password/", HashMap(), requestParams,
        fileRequestParams, {
            val dataString = getResponseData(it, contextView, requestParams,
                fileRequestParams, callback, errorCallback, ::forgotPassword, showMessage)

            if (dataString != null) {
                callback(null)
            }
        }, { errorCallback() }).start()
}