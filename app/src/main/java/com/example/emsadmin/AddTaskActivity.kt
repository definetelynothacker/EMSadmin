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
import com.google.android.material.textfield.TextInputEditText


class AddTaskActivity : AppCompatActivity() {

    private lateinit var spinnerAssignTaskToDep: Spinner
    private lateinit var spinnerAssignTaskToProject: Spinner
    private lateinit var spinnerAssignTaskToEmployee: Spinner

    private lateinit var selectedDepartmentIDs: MutableList<String>
    private lateinit var selectedProjectIDs: MutableList<String>
    private lateinit var selectedTaskIDs: MutableList<String>

    private lateinit var btnCreateAndAssignTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerAssignTaskToDep = findViewById(R.id.spinnerAssignTaskToDep)
        val departmentList = DepartmentManager.getDepartmentList().map{it.getDepartmentName() to it.getDepartmentID()}
        val departmentAdapter = DepartmentCheckboxSpinnerAdapter(this, departmentList)
        selectedDepartmentIDs = departmentAdapter.getSelectedItems()
        spinnerAssignTaskToDep.adapter = departmentAdapter

        spinnerAssignTaskToProject = findViewById(R.id.spinnerAssignTaskToProject)
        val projectList = ProjectManager.projectList.map{it.getProjectName() to it.getProjectID()}
        val projectAdapter = ProjectCheckboxSpinnerAdapter(this, projectList)
        selectedProjectIDs = projectAdapter.getSelectedItems()
        spinnerAssignTaskToProject.adapter = projectAdapter

        spinnerAssignTaskToEmployee = findViewById(R.id.spinnerAssignTaskToEmployee)
        val taskList = TaskManager.getTaskList().map{it.getTaskName() to it.getTaskID()}
        val taskAdapter = TaskCheckboxSpinnerAdapter(this, taskList)
        selectedTaskIDs = taskAdapter.getSelectedItems()
        spinnerAssignTaskToEmployee.adapter = taskAdapter

        btnCreateAndAssignTask = findViewById(R.id.btnCreateAndAssignTask)
        btnCreateAndAssignTask.setOnClickListener{addTaskToList()}
    }
    private fun addTaskToList(){

        val textInputTaskName: EditText = findViewById(R.id.textInputTaskName)
        val editTextStartDate: EditText = findViewById(R.id.editTextStartDate)
        val editTextEndDate: EditText = findViewById(R.id.editTextEndDate)

        val tName = textInputTaskName.text.toString()
        val newTask =  Task(
            taskName = tName,
            taskStartDate = editTextStartDate.text.toString(),
            taskEndDate = editTextEndDate.text.toString()
        )
        TaskManager.getTaskList().add(newTask)
        Toast.makeText(this, "Task: $tName created", Toast.LENGTH_SHORT).show()
    }
}