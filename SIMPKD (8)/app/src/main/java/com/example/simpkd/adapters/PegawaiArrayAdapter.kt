package com.example.simpkd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.simpkd.R
import com.example.simpkd.models.Pegawai
import com.google.android.material.textview.MaterialTextView
import java.util.*

class PegawaiArrayAdapter(
    private val ctx: Context,
    private val items: List<Pegawai>,
    private val resourceId: Int = R.layout.layout_item_list
): ArrayAdapter<Pegawai>(ctx, resourceId, items) {

    private var filteredItems: List<Pegawai> = items

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(ctx).inflate(resourceId, parent,
            false)
        val text = "${filteredItems[position].nama} - ${filteredItems[position].jabatan}"
        view.findViewById<MaterialTextView>(R.id.item_text_view).text = text
        return view
    }

    override fun getItem(position: Int): Pegawai {
        return filteredItems[position]
    }

    override fun getItemId(position: Int): Long {
        return filteredItems[position].id
    }

    override fun getCount(): Int {
        return filteredItems.size
    }

    override fun getFilter(): Filter {
        return object: Filter() {

            override fun performFiltering(p0: CharSequence?): FilterResults {
                val query = p0?.toString()?.lowercase(Locale.ENGLISH)
                val filterResults = FilterResults()

                filterResults.values = if (query.isNullOrEmpty()) items else items.filter {
                    val text = "${it.nama} - ${it.jabatan}"
                    text.lowercase(Locale.ENGLISH).contains(query)
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredItems = p1?.values as List<Pegawai>
                notifyDataSetChanged()
            }

        }
    }
}