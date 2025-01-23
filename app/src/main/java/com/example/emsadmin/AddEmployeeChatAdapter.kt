package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddEmployeeChatAdapter(private val yourEmployeeList: MutableList<Employee>):
    RecyclerView.Adapter<AddEmployeeChatAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val imgViewEmployeeProfileImage2: ImageView = itemView.findViewById(R.id.imgViewEmployeeProfileImage2)
        val tvEmployeeName: TextView = itemView.findViewById(R.id.tvEmployeeName)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedEmployee = yourEmployeeList[position]

                val intent = Intent(itemView.context, ChatActivity::class.java)
                intent.putExtra("employee_id", clickedEmployee.getEmployeeID())
                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddEmployeeChatAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_employee_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddEmployeeChatAdapter.ViewHolder, position: Int) {
        val employee = yourEmployeeList[position]
        holder.imgViewEmployeeProfileImage2
        holder.tvEmployeeName.text = employee.getFullName()
    }

    override fun getItemCount(): Int {
        return yourEmployeeList.size
    }
}