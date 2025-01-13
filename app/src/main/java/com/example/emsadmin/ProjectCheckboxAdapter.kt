package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProjectCheckboxAdapter(private val projectList: MutableList<Project>):
    RecyclerView.Adapter<ProjectCheckboxAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvProjectName: TextView = itemView.findViewById(R.id.tvDepartmentNameAdapter)
        val tvProjectStatus: TextView = itemView.findViewById(R.id.tvProjectStatusAdapter)
        val tvProjectStartDate: TextView = itemView.findViewById(R.id.tvProjectStartDateAdapter)
        val tvProjectEndDate: TextView = itemView.findViewById(R.id.tvProjectEndDateAdapter)
        val pbProjectCompletion: ProgressBar = itemView.findViewById(R.id.pbProjectCompletion)
        val imageViewProject: ImageView = itemView.findViewById(R.id.imageViewDepartmentAdapter)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedDepartment = projectList[position]

                val intent = Intent(itemView.context, DepartmentActivity::class.java)

                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectCheckboxAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.project_checkbox_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectCheckboxAdapter.ViewHolder, position: Int) {
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