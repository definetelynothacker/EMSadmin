package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DepartmentAdapter(private val departmentList: MutableList<Department>):
    RecyclerView.Adapter<DepartmentAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvDepartmentName: TextView = itemView.findViewById(R.id.tvDepartmentName)
        val imageViewDepartment: ImageView = itemView.findViewById(R.id.imageViewDepartment)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedDepartment = departmentList[position]

                val intent = Intent(itemView.context, DepartmentActivity::class.java)
                intent.putExtra("department_name", clickedDepartment.getDepartmentName())
                intent.putExtra("department_manager", clickedDepartment.getDepartmentManager())

                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.department_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DepartmentAdapter.ViewHolder, position: Int) {
        val department = departmentList[position]
        holder.tvDepartmentName.text = department.getDepartmentName()
        holder.imageViewDepartment.setImageResource(R.drawable.default_project_icon)
    }

    override fun getItemCount(): Int {
        return departmentList.size
    }
}