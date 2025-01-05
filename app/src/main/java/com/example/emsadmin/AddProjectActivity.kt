package com.example.emsadmin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddProjectActivity : AppCompatActivity() {

    private lateinit var btnSaveProject: Button
    private lateinit var pName: String
    private lateinit var pSDate: String
    private lateinit var pEDate: String
    private lateinit var spinnerAssignProjectToDepartment: Spinner
    private lateinit var spinnerAssignEmployeesWithinProject: Spinner

    private lateinit var selectedDepartmentIDs: MutableList<String>
    private lateinit var selectedEmployeeIDs: MutableList<String>
    private lateinit var selectedTaskIDs: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_project)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnSaveProject = findViewById(R.id.btnSaveProject)
        //if(fillCreateProjectFields())
        btnSaveProject.setOnClickListener { addProjectToList() }

        spinnerAssignProjectToDepartment = findViewById(R.id.spinnerAssignProjectToDep)
        val departmentList = DepartmentManager.getDepartmentList().map{it.getDepartmentName() to it.getDepartmentID()}
        val departmentAdapter = DepartmentCheckboxSpinnerAdapter(this, departmentList)
        selectedDepartmentIDs = departmentAdapter.getSelectedItems()
        spinnerAssignProjectToDepartment.adapter = departmentAdapter

        spinnerAssignEmployeesWithinProject = findViewById(R.id.spinnerAssignEmployeesWithinProject)
        val employeeList = EmployeeManager.getEmployeeList().map{it.getFullName() to it.getEmployeeID()}
        val employeeAdapter = EmployeeCheckboxSpinnerAdapter(this, employeeList)
        selectedEmployeeIDs = employeeAdapter.getSelectedItems()
        spinnerAssignProjectToDepartment.adapter = employeeAdapter

        spinnerAssignEmployeesWithinProject = findViewById(R.id.spinnerAssignEmployeesWithinProject)
        val taskList = TaskManager.getTaskList().map{it.getTaskName() to it.getTaskID()}
        val taskAdapter = TaskCheckboxSpinnerAdapter(this, taskList)
        selectedTaskIDs = taskAdapter.getSelectedItems()
        spinnerAssignProjectToDepartment.adapter = taskAdapter
    }

    private fun createProjectObj(
        projectName: String,
        projectStartDate: String,
        projectEndDate: String
    ): Project {
        return Project(
            projectName = projectName,
            startDate = projectStartDate,
            endDate = projectEndDate,//all other values remain null for now,
            departmentIDList = selectedDepartmentIDs,
            employeeIDList = selectedEmployeeIDs,
            taskIDList = selectedTaskIDs
        )
    }

    private fun fillCreateProjectFields(): Boolean {
        val inputEnterProjectName: EditText = findViewById(R.id.inputEnterProjectName)
        val etStartDate: EditText = findViewById(R.id.etStartDate)
        val etEndDate: EditText = findViewById(R.id.etEndDate)

        pName = inputEnterProjectName.getText().toString()
        pSDate = etStartDate.getText().toString()
        pEDate = etEndDate.getText().toString()
        /*
        var isValid = true

        if(TextUtils.isEmpty(pName)){
            inputEnterProjectName.error = "Please enter Project Name"
            isValid = false
        }
        if(TextUtils.isEmpty(pSDate)){
            inputEnterProjectName.error = "Please enter Start Date"
            isValid = false
        }
        if(TextUtils.isEmpty(pEDate)){
            inputEnterProjectName.error = "Please enter Ent Date"
            isValid = false
        }*/
        return true
    }

    private fun addProjectToList() {
        val inputEnterProjectName: EditText = findViewById(R.id.inputEnterProjectName)
        val etStartDate: EditText = findViewById(R.id.etStartDate)
        val etEndDate: EditText = findViewById(R.id.etEndDate)

        pName = inputEnterProjectName.getText().toString()
        pSDate = etStartDate.getText().toString()
        pEDate = etEndDate.getText().toString()

        val newProject = createProjectObj(pName, pSDate, pEDate)
        //add project
        ProjectManager.projectList.add(newProject)
        Toast.makeText(this, "Project: $pName created", Toast.LENGTH_SHORT).show()
    }
}