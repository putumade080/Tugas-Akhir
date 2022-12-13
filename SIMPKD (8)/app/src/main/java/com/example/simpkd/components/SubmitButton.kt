package com.example.simpkd.components

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.example.simpkd.utils.toPx
import com.google.android.material.button.MaterialButton

class SubmitButton(
    ctx: Context,
    attr: AttributeSet
): MaterialButton(ctx, attr) {
    private val prevText: CharSequence = text

    init {
        setPadding(0, (20).toPx(), 0, (20).toPx())
    }

    fun submit() {
        val loadingText = "Mohon tunggu sejenak"
        text = loadingText
        isClickable = false
        isEnabled = false
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
    }

    fun submitFinish() {
        isEnabled = true
        isClickable = true
        text = prevText
    }
}