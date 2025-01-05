package com.example.emsadmin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class AddEmployeeActivity : AppCompatActivity() {

    private lateinit var inputEmployeeFName: TextInputEditText
    private lateinit var inputEmployeeLName: TextInputEditText
    private lateinit var btnSaveEmployee: Button
    private lateinit var lName: String
    private lateinit var fName: String
    private lateinit var spinnerAssignEmployeeToDep: Spinner
    private lateinit var spinnerAssignEmployeeToProject: Spinner
    private lateinit var spinnerAssignEmployeeToTask: Spinner

    private lateinit var selectedDepartmentIDs: MutableList<String>
    private lateinit var selectedProjectIDs: MutableList<String>
    private lateinit var selectedTaskIDs: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnSaveEmployee = findViewById(R.id.btnSaveEmployee)
        btnSaveEmployee.setOnClickListener{addEmployeeToList()}

        spinnerAssignEmployeeToDep = findViewById(R.id.spinnerAssignEmployeeToDep)
        val departmentList = DepartmentManager.getDepartmentList().map{it.getDepartmentName() to it.getDepartmentID()}
        val departmentAdapter = DepartmentCheckboxSpinnerAdapter(this, departmentList)
        selectedDepartmentIDs = departmentAdapter.getSelectedItems()
        spinnerAssignEmployeeToDep.adapter = departmentAdapter

        spinnerAssignEmployeeToProject = findViewById(R.id.spinnerAssignEmployeeToProject)
        val projectList = ProjectManager.projectList.map{it.getProjectName() to it.getProjectID()}
        val projectAdapter = ProjectCheckboxSpinnerAdapter(this, projectList)
        selectedProjectIDs = projectAdapter.getSelectedItems()
        spinnerAssignEmployeeToProject.adapter = projectAdapter

        spinnerAssignEmployeeToTask = findViewById(R.id.spinnerAssignEmployeeToTask)
        val taskList = TaskManager.getTaskList().map{it.getTaskName() to it.getTaskID()}
        val taskAdapter = TaskCheckboxSpinnerAdapter(this, taskList)
        selectedTaskIDs = taskAdapter.getSelectedItems()
        spinnerAssignEmployeeToTask.adapter = taskAdapter
    }
    private fun createEmployee(): Employee{
        inputEmployeeFName = findViewById(R.id.inputEmployeeFname)
        inputEmployeeLName = findViewById(R.id.inputEmployeeLname)
        lName = inputEmployeeLName.getText().toString()
        fName = inputEmployeeFName.getText().toString()
        return Employee(employeeFName = fName,
                        employeeLName = lName,
                        departmentIDList = selectedDepartmentIDs,
                        projectIDList = selectedProjectIDs,
                        taskIDList = selectedTaskIDs
        )
    }
    private fun addEmployeeToList(){
        val newEmployee = createEmployee()
        EmployeeManager.getEmployeeList().add(newEmployee)
        Toast.makeText(this, "Employee: $fName $lName created", Toast.LENGTH_SHORT).show()
    }
}