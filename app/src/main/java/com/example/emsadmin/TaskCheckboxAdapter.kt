package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskCheckboxAdapter(private val taskList: MutableList<Task>):
    RecyclerView.Adapter<TaskCheckboxAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvTaskNameAdapter: TextView = itemView.findViewById(R.id.tvDepartmentNameAdapter)
        val imageViewTaskAdapter: ImageView = itemView.findViewById(R.id.imageViewDepartmentAdapter)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedTask = taskList[position]

                val intent = Intent(itemView.context, TaskActivity::class.java)

                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCheckboxAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_checkbox_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskCheckboxAdapter.ViewHolder, position: Int) {
        val task = taskList[position]
        holder.tvTaskNameAdapter.text = task.getTaskName()
        holder.imageViewTaskAdapter.setImageResource(R.drawable.task_icon)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
    fun updateTaskList(newList: MutableList<Task>){
        taskList.clear()
        val posStart = taskList.size-1
        val posEnd = newList.size-1
        taskList.addAll(newList)
        notifyItemRangeChanged(posStart, posEnd)
    }
}