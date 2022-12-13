package com.example.simpkd.components

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.content.res.getColorOrThrow
import com.example.simpkd.R
import com.example.simpkd.utils.toPx
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.component_auto_complete_chip.view.*
import java.lang.IllegalArgumentException

class AutoCompleteChip(ctx: Context, attrs: AttributeSet? = null): MaterialCardView(ctx, attrs) {
    private val view = LayoutInflater.from(ctx).inflate(R.layout.component_auto_complete_chip,
        this, true)
    private val chips: ArrayList<String> = arrayListOf()
    private val chipIds: ArrayList<Long> = arrayListOf()
    private var listenerCallback: ((chips: List<String>, chipIds: List<Long>) -> Unit)? = null

    var label = ""
    var addOnDone = false
    var background = ContextCompat.getColor(view.context, android.R.color.white)
    var adapter: ArrayAdapter<*>? = null
        set(value) {
            field = value
            view.autocomplete.setAdapter(adapter)
        }

    fun addChip(str: String, id: Long) {
        view.autocomplete.setText("")

        if (chips.indexOfFirst { it.lowercase() == str.lowercase() } > -1) {
            return
        }

        val chip = Chip(view.context).apply {
            layoutParams = MarginLayoutParams(
                MarginLayoutParams.WRAP_CONTENT,
                MarginLayoutParams.WRAP_CONTENT).apply {
                marginEnd = (8).toPx()
            }
            text = str
            isCloseIconVisible = true
            setOnCloseIconClickListener {
                chips.removeAt(chips.indexOfFirst { v -> v.lowercase() == str.lowercase() })
                chipIds.remove(id)
                view.flex_container.removeView(it)
                if (listenerCallback != null) listenerCallback?.invoke(chips, chipIds)
            }
        }

        view.flex_container.addView(chip, view.flex_container.childCount - 1)
        chips.add(str)
        chipIds.add(id)
        if (listenerCallback != null) listenerCallback?.invoke(chips, chipIds)
    }

    init {
        val typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.AutoCompleteChip)
        label = typedArray.getString(R.styleable.AutoCompleteChip_android_label) ?: label
        addOnDone = typedArray.getBoolean(R.styleable.AutoCompleteChip_addOnDone, false)

        try {
            background = typedArray.getColorOrThrow(R.styleable.AutoCompleteChip_android_background)
        } catch (e: IllegalArgumentException) {}

        typedArray.recycle()

        setCardBackgroundColor(background)
        view.label.text = label

        view.autocomplete.setOnFocusChangeListener { _, hasFocus ->
            view.container.apply {
                strokeWidth = if (hasFocus) (2).toPx() else (1).toPx()
                strokeColor = ContextCompat.getColor(view.context,
                    if (hasFocus) R.color.primaryColor else R.color.colorOnSurface)
            }
            view.label.apply {
                setTextColor(
                    ContextCompat.getColor(view.context,
                    if (hasFocus) R.color.primaryColor else R.color.colorOnSurface))
                typeface = if (hasFocus) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
            }
        }

        view.autocomplete.setOnItemClickListener { _, _, _, id ->
            addChip("${view.autocomplete.text}", id)
        }

        if (addOnDone) {
            view.autocomplete.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addChip("${view.autocomplete.text}", chips.size.toLong())
                }
                true
            }
        }
    }

    fun getChips(): List<String> {
        return chips.toList()
    }

    fun getChipIds(): List<Long> {
        return chipIds.toList()
    }

    fun setOnChipChangeListener(callback: (chips: List<String>, chipIds: List<Long>) -> Unit) {
        listenerCallback = callback
    }
}