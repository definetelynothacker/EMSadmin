package com.example.emsadmin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddDepartmentActivity : AppCompatActivity() {

    private lateinit var dName: String
    private lateinit var dManager: String
    private lateinit var btnSaveDepartment: Button
    private lateinit var spinnerAssignProjectToDep2: Spinner
    private lateinit var spinnerAssignEmployeeToDep2: Spinner
    private lateinit var spinnerAssignTaskToDep2: Spinner

    private lateinit var selectedProjectIDs: MutableList<String>
    private lateinit var selectedEmployeeIDs: MutableList<String>
    private lateinit var selectedTaskIDs: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_department)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //selectedProjectIDs = mutableListOf()
        //selectedEmployeeIDs = mutableListOf()
        //selectedTaskIDs = mutableListOf()

        btnSaveDepartment = findViewById(R.id.btnSaveDepartment)
        btnSaveDepartment.setOnClickListener{addDepartmentToList()}

        spinnerAssignProjectToDep2 = findViewById(R.id.spinnerAssignProjectToDep2)
        val projectList = ProjectManager.projectList.map{it.getProjectName() to it.getProjectID()}
        val projectAdapter = ProjectCheckboxSpinnerAdapter(this, projectList)
        selectedProjectIDs = projectAdapter.getSelectedItems()//to assign to department obj
        spinnerAssignProjectToDep2.adapter = projectAdapter

        spinnerAssignEmployeeToDep2 = findViewById(R.id.spinnerAssignEmployeeToDep2)
        val employeeList = EmployeeManager.getEmployeeList().map{it.getFullName() to it.getEmployeeID()}
        val employeeAdapter = EmployeeCheckboxSpinnerAdapter(this, employeeList)
        selectedEmployeeIDs = employeeAdapter.getSelectedItems()//to assign to department obj
        spinnerAssignEmployeeToDep2.adapter = employeeAdapter

        spinnerAssignTaskToDep2 = findViewById(R.id.spinnerAssignTaskToDep2)
        val taskList = TaskManager.getTaskList().map{it.getTaskName() to it.getTaskID()}
        val taskAdapter = TaskCheckboxSpinnerAdapter(this, taskList)
        selectedTaskIDs = taskAdapter.getSelectedItems()//to assign to department obj
        spinnerAssignTaskToDep2.adapter = taskAdapter
    }
    private fun createDepartmentObj(departmentName: String, departmentManager: String): Department{//convert departmentManager to Employee obj instead of string eventually
        return Department(
            departmentName = departmentName,
            departmentManager = departmentManager,
            projectIDList = selectedProjectIDs,
            employeeIDList = selectedEmployeeIDs,
            taskIDList = selectedTaskIDs
            //all other values remain null for now
        )
    }
    private fun addDepartmentToList(){
        val inputEnterDepartmentName: EditText = findViewById(R.id.inputEnterDepartmentName)

        dName = inputEnterDepartmentName.getText().toString()
        dManager = "NULL for now"

        val newDepartment = createDepartmentObj(dName, dManager)
        //add Department
        DepartmentManager.getDepartmentList().add(newDepartment)
        Toast.makeText(this, "Department: $dName created", Toast.LENGTH_SHORT).show()
    }
}