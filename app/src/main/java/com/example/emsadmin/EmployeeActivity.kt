package com.example.emsadmin

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmployeeActivity : AppCompatActivity() {

    private lateinit var tvEmployeeNameDetails: TextView

    private lateinit var spinnerEmployeeList: Spinner

    private lateinit var rcvDepartmentEmployeeBelongsTo: RecyclerView
    private lateinit var rcvProjectsEmployeesAssignedTo: RecyclerView
    private lateinit var rcvTaskAssignedToEmployee: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvEmployeeNameDetails = findViewById(R.id.tvEmployeeNameDetails)

        spinnerEmployeeList = findViewById(R.id.spinnerEmployeeList)

        rcvDepartmentEmployeeBelongsTo = findViewById(R.id.rcvDepartmentEmployeeBelongsTo)
        rcvProjectsEmployeesAssignedTo = findViewById(R.id.rcvProjectsEmployeesAssignedTo)
        rcvTaskAssignedToEmployee = findViewById(R.id.rcvTaskAssignedToEmployee)

        val employeeList = EmployeeManager.getEmployeeList().map{it.getFullName()}
        val adapterSpinner = ArrayAdapter(this, R.layout.employee_spinner_text_only, employeeList)
        spinnerEmployeeList.adapter = adapterSpinner
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEmployeeList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                tvEmployeeNameDetails.text = employeeList[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val departments = DepartmentManager.getDepartmentList()
        val adapterDep = DepartmentCheckboxAdapter(departments)
        rcvDepartmentEmployeeBelongsTo.adapter = adapterDep
        rcvDepartmentEmployeeBelongsTo.layoutManager = LinearLayoutManager(this)

        val projects = ProjectManager.projectList
        val adapterProj = ProjectCheckboxAdapter(projects)
        rcvProjectsEmployeesAssignedTo.adapter = adapterProj
        rcvProjectsEmployeesAssignedTo.layoutManager = LinearLayoutManager(this)

        val tasks = TaskManager.getTaskList()
        val adapterTask = TaskCheckboxAdapter(tasks)
        rcvTaskAssignedToEmployee.adapter = adapterTask
        rcvTaskAssignedToEmployee.layoutManager = LinearLayoutManager(this)
    }
}