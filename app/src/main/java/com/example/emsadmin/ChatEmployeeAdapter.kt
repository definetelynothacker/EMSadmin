package com.example.emsadmin


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatEmployeeAdapter(private val employeeList: MutableList<Employee>):
    RecyclerView.Adapter<ChatEmployeeAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val imgBtnEmployeeProfileImage2: ImageButton = itemView.findViewById(R.id.imgBtnEmployeeProfileImage2)
        val tvEmployeeName: TextView = itemView.findViewById(R.id.tvEmployeeName)
        /*
        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedChat = yourChatList[position]

                val intent = Intent(itemView.context, ProjectActivity::class.java)
                intent.putExtra("project_name", clickedChat.getProjectName())
                intent.putExtra("project_status", clickedChat.getStatus())

                itemView.context.startActivity(intent)
            }
        }*/

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatEmployeeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_employee_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatEmployeeAdapter.ViewHolder, position: Int) {
        val employee = employeeList[position]
        holder.tvEmployeeName.text = employee.getFullName()
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }
}