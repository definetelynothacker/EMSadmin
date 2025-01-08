package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(private val employeeList: MutableList<Employee>):
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvEmployeeNameAdapter: TextView = itemView.findViewById(R.id.tvEmployeeNameAdapter)
        /*
        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedProject = employeeList[position]

                val intent = Intent(itemView.context, ProjectActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }*/

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeAdapter.ViewHolder, position: Int) {
        val employee = employeeList[position]
        holder.tvEmployeeNameAdapter.text = employee.getFullName()
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }
}