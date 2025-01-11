package com.example.emsadmin

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProjectActivity : AppCompatActivity(){

    private lateinit var spinnerProjects: Spinner
    private lateinit var rcvDepartmentsThatContainProject: RecyclerView
    private lateinit var rcvEmployeesInProject: RecyclerView
    private lateinit var rcvTaskListInProject: RecyclerView

    private lateinit var temporaryDepList: MutableList<Department>
    private lateinit var temporaryEmpList: MutableList<Employee>
    private lateinit var temporaryTaskList: MutableList<Task>

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_project)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){ v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spinnerProjects = findViewById(R.id.spinnerProjects)
        val projects = ProjectManager.projectList.map{it.getProjectName() to it.getProjectID()}
        val projectNames = projects.map{it.first}
        val adapter = ArrayAdapter(this, R.layout.project_spinner_text_only, projectNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProjects.adapter = adapter
        spinnerProjects.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        rcvDepartmentsThatContainProject = findViewById(R.id.rcvDepartmentsThatContainProject)
        temporaryDepList = DepartmentManager.getDepartmentList()
        val adapterDep = DepartmentAdapter(temporaryDepList)
        rcvDepartmentsThatContainProject.adapter = adapterDep
        rcvDepartmentsThatContainProject.layoutManager = LinearLayoutManager(this)

        rcvEmployeesInProject = findViewById(R.id.rcvEmployeeListInProject)
        temporaryEmpList = EmployeeManager.getEmployeeList()
        val adapterEmp = EmployeeAdapter(temporaryEmpList)
        rcvEmployeesInProject.adapter = adapterEmp
        rcvEmployeesInProject.layoutManager = LinearLayoutManager(this)

        rcvTaskListInProject = findViewById(R.id.rcvTaskListInProject)
        temporaryTaskList = TaskManager.getTaskList()
        val adapterTask = TaskAdapter(temporaryTaskList)
        rcvTaskListInProject.adapter = adapterTask
        rcvTaskListInProject.layoutManager = LinearLayoutManager(this)
    }
}