package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){ v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navigateToAddDepartmentActivity()
        navigateToAddProjectActivity()
        navigateToChatHomeActivity()
        setUpPopup()
        renderProjectList()
        renderDepartmentList()
    }
    private fun navigateToAddDepartmentActivity(){
        val btnMoreDepartmentOptions: Button = findViewById(R.id.btnCreateDepartmentOption)
        btnMoreDepartmentOptions.setOnClickListener{
            val intent = Intent(this, AddDepartmentActivity::class.java)
            startActivity(intent)
        }
    }
    private fun navigateToAddProjectActivity(){
        val btnMoreProjects: Button = findViewById(R.id.btnCreateProjectOption)
        btnMoreProjects.setOnClickListener{
            val intent = Intent(this, AddProjectActivity::class.java)
            startActivity(intent)
        }
    }
    private fun navigateToChatHomeActivity(){
        val imgBtnChat: ImageButton = findViewById(R.id.imgBtnChat)
        imgBtnChat.setOnClickListener{
            val intent = Intent(this, ChatHomeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun navigateToAddEmployeeActivity(){
        val btnCreateEmployee1: Button = findViewById(R.id.btnCreateEmployee)
        btnCreateEmployee1.setOnClickListener{
            val intent = Intent(this, AddEmployeeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setUpPopup(){
        val imgBtnAddAny: ImageButton = findViewById(R.id.imgBtnAddAny)
        imgBtnAddAny.setOnClickListener{ view->
            val inflater: LayoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView: View = inflater.inflate(R.layout.add_any_popup, null)

            val popupWindow = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            val btnCreateDepartmentPopup: Button = popupView.findViewById(R.id.btnCreateDepartmentPopup)
            val btnCreateProjectPopup: Button = popupView.findViewById(R.id.btnCreateProjectPopup)
            val btnCreateEmployeePopup: Button = popupView.findViewById(R.id.btnCreateEmployeePopup)
            val btnCreateTaskPopup: Button = popupView.findViewById(R.id.btnCreateTaskPopup)

            btnCreateDepartmentPopup.setOnClickListener{
                popupWindow.dismiss()
                val intent = Intent(this, AddDepartmentActivity::class.java)
                startActivity(intent)
            }
            btnCreateProjectPopup.setOnClickListener{
                popupWindow.dismiss()
                val intent = Intent(this, AddProjectActivity::class.java)
                startActivity(intent)
            }
            btnCreateEmployeePopup.setOnClickListener{
                popupWindow.dismiss()
                val intent = Intent(this, AddEmployeeActivity::class.java)
                startActivity(intent)
            }
            btnCreateTaskPopup.setOnClickListener{
                popupWindow.dismiss()
                val intent = Intent(this, AddTaskActivity::class.java)
                startActivity(intent)
            }
            popupWindow.elevation = 10f
            popupWindow.setBackgroundDrawable(
                ContextCompat.getDrawable(this, android.R.color.transparent)
            )
            popupWindow.isOutsideTouchable = true
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
        }
    }

    private fun renderProjectList(){
        val rcvProjectsList1: RecyclerView = findViewById(R.id.rcvProjectsList1)
        val projectsList: MutableList<Project> = ProjectManager.projectList
        val projectAdapterInst = ProjectAdapter(projectsList)
        rcvProjectsList1.adapter = projectAdapterInst
        rcvProjectsList1.layoutManager = LinearLayoutManager(this)
    }
    private fun renderDepartmentList(){
        val rcvDepartmentsList1: RecyclerView = findViewById(R.id.rcvDepartmentsList1)
        val departmentsList: MutableList<Department> = DepartmentManager.getDepartmentList()
        val departmentAdapterInst = DepartmentAdapter(departmentsList)
        rcvDepartmentsList1.adapter = departmentAdapterInst
        rcvDepartmentsList1.layoutManager = LinearLayoutManager(this)
    }
}