package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Token(var access_token: String, var refresh_token: String,
                 var change_password_status: Boolean): Parcelable