package com.example.emsadmin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView

class TaskCheckboxSpinnerAdapter(
    private val context: Context,
    private val items: List<Pair<String, String>>
) : BaseAdapter() {
    private val selectedItems: MutableList<String> = mutableListOf()
    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.task_spinner_item, parent, false)
        val checkBox = view.findViewById<CheckBox>(R.id.checkboxTask)
        val textView = view.findViewById<TextView>(R.id.tvItemTask)

        val item = items[position]
        textView.text = item.first
        checkBox.isChecked = selectedItems.contains(item.second)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedItems.add(item.second)
            } else {
                selectedItems.remove(item.second)
            }
        }

        return view
    }
    fun getSelectedItems(): MutableList<String>{
        return selectedItems
    }
}

