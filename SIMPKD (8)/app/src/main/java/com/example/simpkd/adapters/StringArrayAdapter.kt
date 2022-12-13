package com.example.simpkd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.simpkd.R
import com.google.android.material.textview.MaterialTextView
import java.util.*
import kotlin.collections.ArrayList

class StringArrayAdapter(
    private val ctx: Context,
    private val items: ArrayList<String>,
    private val resourceId: Int = R.layout.layout_item_list
): ArrayAdapter<String>(ctx, resourceId, items) {

    private var filteredItems: List<String> = items

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(ctx).inflate(resourceId, parent,
            false)
        view.findViewById<MaterialTextView>(R.id.item_text_view).text = filteredItems[position]
        return view
    }

    override fun getItem(position: Int): String {
        return filteredItems[position]
    }

    override fun getCount(): Int {
        return filteredItems.size
    }

    override fun getPosition(item: String?): Int {
        return items.indexOfFirst { it.lowercase() == item }
    }

    override fun getFilter(): Filter {
        return object: Filter() {

            override fun performFiltering(p0: CharSequence?): FilterResults {
                val query = p0?.toString()?.lowercase(Locale.ENGLISH)
                val filterResults = FilterResults()

                filterResults.values = if (query.isNullOrEmpty()) items else items.filter {
                    it.lowercase(Locale.ENGLISH).contains(query)
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredItems = p1?.values as List<String>
                notifyDataSetChanged()
            }

        }
    }
}