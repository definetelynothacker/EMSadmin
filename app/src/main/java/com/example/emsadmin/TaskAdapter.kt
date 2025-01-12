package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val taskList: MutableList<Task>):
    RecyclerView.Adapter<TaskAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvTaskNameAdapter: TextView = itemView.findViewById(R.id.tvTaskNameAdapter)
        val imageViewTaskAdapter: ImageView = itemView.findViewById(R.id.imageViewTaskAdapter)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedTask = taskList[position]

                val intent = Intent(itemView.context, TaskActivity::class.java)

                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
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