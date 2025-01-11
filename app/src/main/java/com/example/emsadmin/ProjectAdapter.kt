package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProjectAdapter(private val projectList: MutableList<Project>):
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvProjectName: TextView = itemView.findViewById(R.id.tvTaskNameAdapter)
        val tvProjectStatus: TextView = itemView.findViewById(R.id.tvTaskStatusAdapter)
        val tvProjectStartDate: TextView = itemView.findViewById(R.id.tvTaskStartDateAdapter)
        val tvProjectEndDate: TextView = itemView.findViewById(R.id.tvTaskEndDateAdapter)
        val pbProjectCompletion: ProgressBar = itemView.findViewById(R.id.pbProjectCompletion)
        val imageViewProject: ImageView = itemView.findViewById(R.id.imageViewTaskAdapter)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedProject = projectList[position]

                val intent = Intent(itemView.context, ProjectActivity::class.java)
                intent.putExtra("project_name", clickedProject.getProjectName())
                intent.putExtra("project_status", clickedProject.getStatus())

                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.project_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectAdapter.ViewHolder, position: Int) {
        val project = projectList[position]
        holder.tvProjectName.text = project.getProjectName()
        holder.tvProjectStatus.text = project.getStatus()
        holder.tvProjectStartDate.text = project.getStartDate()
        holder.tvProjectEndDate.text = project.getEndDate()
        holder.pbProjectCompletion.progress = project.getProgress()
        holder.imageViewProject.setImageResource(R.drawable.default_project_icon)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }
    fun updateProjectList(newList: MutableList<Project>){
        projectList.clear()
        val posStart = projectList.size-1
        val posEnd = newList.size-1
        projectList.addAll(newList)
        notifyItemRangeChanged(posStart, posEnd)
    }
}