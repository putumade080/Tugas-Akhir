package com.example.simpkd.apis

import android.content.Context
import android.content.Intent
import android.view.View
import com.android.volley.Request
import com.example.simpkd.activities.auth.LoginActivity
import com.example.simpkd.models.PemeliharaanKendaraan
import com.example.simpkd.services.FileDataPart
import com.example.simpkd.services.VolleyMultipartRequest
import com.example.simpkd.utils.Constants
import com.example.simpkd.utils.getResponseData
import com.google.gson.Gson

fun getPemeliharaanKendaraan(contextView: View, requestParams: HashMap<String, String>,
                          fileRequestParams: HashMap<String, FileDataPart>, callback: (Any?) -> Unit,
                          errorCallback: () -> Unit, showMessage: Boolean = true) {
    val sharedPreferences = contextView.context.getSharedPreferences("SESSIONS",
        Context.MODE_PRIVATE)
    val accessToken = sharedPreferences.getString("ACCESS_TOKEN", null)

    if (accessToken != null) {
        val headers = HashMap<String, String>()
        headers["x-auth-token"] = "Bearer $accessToken"

        VolleyMultipartRequest(contextView.context, Request.Method.GET,
            "${Constants.BASE_URL}/pemeliharaan-kendaraan/", headers, requestParams,
            fileRequestParams, {
                val dataString = getResponseData(
                    it, contextView, requestParams,
                    fileRequestParams, callback, errorCallback, ::getSuratPerintahTugas, showMessage
                )

                if (dataString != null) {
                    val data = Gson().fromJson(dataString, Array<PemeliharaanKendaraan>::class.java)
                    callback(data)
                }
            }, { errorCallback() }).start()
        return
    }

    val intent = Intent(contextView.context, LoginActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    contextView.context.startActivity(intent)
}