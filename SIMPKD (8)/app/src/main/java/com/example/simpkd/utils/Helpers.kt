package com.example.simpkd.utils

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.net.Uri
import android.os.Parcelable
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.android.volley.NetworkResponse
import com.android.volley.toolbox.HttpHeaderParser
import com.example.simpkd.R
import com.example.simpkd.apis.refreshToken
import com.example.simpkd.services.FileDataPart
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap

@Parcelize
data class FileInfo(var mimeType: String = "", var displayName: String = "",
                    var extension: String = "", var size: Long = 0): Parcelable

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun String.isEmail(): Boolean {
    val regexp = Regex("^[a-zA-Z0-9.!#\$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$")
    return regexp.matches(this)
}

fun formatFullDate(dateString: String): String {
    val dateTokens = dateString.split("-")
    return "${dateTokens[2]} ${Constants.MONTHS[dateTokens[1].toInt() - 1]} ${dateTokens[0]}"
}

fun showDatePicker(context: Context, callback: (year: Int, month: Int, dayOfMonth: Int) -> Unit,
                   year: Int? = null, month: Int? = null, dayOfMonth: Int? = null) {
    val calendar = Calendar.getInstance()
    val datepicker = DatePickerDialog(context,
        {_, y, m, d -> callback(y, m, d)},
        year ?: calendar[Calendar.YEAR],
        month ?: calendar[Calendar.MONTH],
        dayOfMonth ?: calendar[Calendar.DAY_OF_MONTH])
    datepicker.show()
}

fun snackbarSuccess(
    contextView: View,
    message: String,
    length: Int
): Snackbar {
    return Snackbar.make(contextView, message, length).apply {
        setBackgroundTint(ContextCompat.getColor(context, R.color.green))
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
    }
}

fun snackbarWarning(
    contextView: View,
    message: String,
    length: Int
): Snackbar {
    return Snackbar.make(contextView, message, length).apply {
        setBackgroundTint(ContextCompat.getColor(context, R.color.yellow))
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
    }
}

fun snackbarError(
    contextView: View,
    message: String,
    length: Int
): Snackbar {
    return Snackbar.make(contextView, message, length).apply {
        setBackgroundTint(ContextCompat.getColor(context, R.color.red))
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
    }
}

fun choosePhoto(): Intent {
    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    val intentChooser = Intent.createChooser(galleryIntent, "Pilih sumber foto")
    intentChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
    return intentChooser
}

fun chooseDocument(mimes: Array<String> = arrayOf("image/*", "application/pdf")): Intent {
    val intent = Intent().apply {
        action = Intent.ACTION_OPEN_DOCUMENT
        type = "*/*"
        addCategory(Intent.CATEGORY_OPENABLE)
        putExtra(Intent.EXTRA_MIME_TYPES, mimes)
    }
    return intent
}

fun getFileInfo(context: Context, uri: Uri?): FileInfo? {
    if (uri != null) {
        val fileInfo = FileInfo()
        fileInfo.mimeType = context.contentResolver.getType(uri)!!
        context.contentResolver.query(uri, null, null, null, null)?.use {
            val nameColumnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val sizeColumnIndex = it.getColumnIndex(OpenableColumns.SIZE)
            it.moveToFirst()
            fileInfo.displayName = it.getString(nameColumnIndex)
            fileInfo.extension = fileInfo.displayName.split(".").last()
            fileInfo.size = it.getLong(sizeColumnIndex)
        }
        return fileInfo
    }
    return null
}

fun getFileByteArray(context: Context, intent: Intent): ByteArray {
    val uri = intent.data

    if (uri != null) {
        return context.contentResolver.openInputStream(uri)!!.buffered().use { it.readBytes() }
    }

    val bitmap = intent.extras!!.get("data") as Bitmap
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    return stream.toByteArray()
}

fun getResponseData(response: NetworkResponse, contextView: View,
                    requestParams: HashMap<String, String>,
                    fileRequestParams: HashMap<String, FileDataPart>,
                    successCallback: (Any?) -> Unit,
                    errorCallback: () -> Unit,
                    callback: (
                        View, HashMap<String, String>, HashMap<String, FileDataPart>,
                        (Any?) -> Unit, () -> Unit, Boolean) -> Unit,
                    showMessage: Boolean = true): String? {
    val json = String(response.data, charset(HttpHeaderParser.parseCharset(response.headers)))
    Log.e("JSON", json)
    val responseObject = JSONObject(json)
    val message = responseObject.getString("message")

    when (responseObject.getInt("status_code")) {
        200 -> {
            if (showMessage) snackbarSuccess(contextView, message, Snackbar.LENGTH_LONG).show()
            return responseObject.getString("data")
        }
        400 -> {
            if (showMessage) snackbarWarning(contextView, message, Snackbar.LENGTH_LONG).show()
            errorCallback()
        }
        401 -> {
            refreshToken(contextView, requestParams, fileRequestParams, successCallback,
                errorCallback, callback, showMessage)
        }
        500 -> {
            if (showMessage) snackbarError(contextView, message, Snackbar.LENGTH_LONG).show()
            errorCallback()
        }
    }

    return null
}