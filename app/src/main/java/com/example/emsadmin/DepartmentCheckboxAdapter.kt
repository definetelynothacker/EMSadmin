package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DepartmentCheckboxAdapter(private val departmentList: MutableList<Department>):
    RecyclerView.Adapter<DepartmentCheckboxAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvDepartmentNameAdapter: TextView = itemView.findViewById(R.id.tvDepartmentNameAdapter)
        val imageViewDepartmentAdapter: ImageView = itemView.findViewById(R.id.imageViewDepartmentAdapter)
        val checkBoxDepartmentAdapter: CheckBox = itemView.findViewById(R.id.checkBoxDepartmentAdapter)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedDepartment = departmentList[position]

                val intent = Intent(itemView.context, DepartmentActivity::class.java)

                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentCheckboxAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.department_checkbox_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DepartmentCheckboxAdapter.ViewHolder, position: Int) {
        val department = departmentList[position]
        holder.tvDepartmentNameAdapter.text = department.getDepartmentName()
        holder.imageViewDepartmentAdapter.setImageResource(R.drawable.department_icon)
    }

    override fun getItemCount(): Int {
        return departmentList.size
    }
    fun updateDepartmentList(newList: MutableList<Department>){
        departmentList.clear()
        val posStart = departmentList.size-1
        val posEnd = newList.size-1
        departmentList.addAll(newList)
        notifyItemRangeChanged(posStart, posEnd)
    }
}