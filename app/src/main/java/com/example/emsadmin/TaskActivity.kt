package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskActivity : AppCompatActivity() {

    private lateinit var spinnerTask: Spinner

    private lateinit var tvTaskName: TextView
    private lateinit var tvTaskStatus: TextView
    private lateinit var tvTaskStartDateActivity: TextView
    private lateinit var tvTaskEndDateActivity: TextView

    private lateinit var tvDepartmentsThatHaveTask: TextView
    private lateinit var tvProjectsThatHaveTask: TextView
    private lateinit var tvEmployeesThatHaveTask: TextView

    private lateinit var rcvDepartmentsThatHaveTask: RecyclerView
    private lateinit var rcvProjectsThatHaveTask: RecyclerView
    private lateinit var rcvEmployeesThatHaveTask: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerTask = findViewById(R.id.spinnerTask)

        tvTaskName = findViewById(R.id.tvTaskName)
        tvTaskStatus = findViewById(R.id.tvTaskStatus)
        tvTaskStartDateActivity = findViewById(R.id.tvTaskStartDateActivity)
        tvTaskEndDateActivity = findViewById(R.id.tvTaskEndDateActivity)

        tvDepartmentsThatHaveTask = findViewById(R.id.tvDepartmentsThatHaveTask)
        tvProjectsThatHaveTask = findViewById(R.id.tvProjectsThatHaveTask)
        tvEmployeesThatHaveTask = findViewById(R.id.tvEmployeesThatHaveTask)

        rcvDepartmentsThatHaveTask = findViewById(R.id.rcvDepartmentsThatHaveTask)
        rcvProjectsThatHaveTask = findViewById(R.id.rcvProjectsThatHaveTask)
        rcvEmployeesThatHaveTask = findViewById(R.id.rcvEmployeesThatHaveTask)

        val taskList = TaskManager.getTaskList()
        val taskNames = taskList.map{it.getTaskName() to it.getTaskStatus()}
        val taskDates = taskList.map{it.getTaskStartDate() to it.getTaskEndDate()}

        val adapterSpinner = ArrayAdapter(this, R.layout.task_spinner_text_only, taskNames)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTask.adapter = adapterSpinner

        spinnerTask.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                tvTaskName.text = taskNames[position].first
                tvTaskStatus.text = taskNames[position].second
                tvTaskStartDateActivity.text = taskDates[position].first
                tvTaskEndDateActivity.text = taskDates[position].second
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val temporaryDepList = DepartmentManager.getDepartmentList()
        val adapterDep = DepartmentCheckboxAdapter(temporaryDepList)
        rcvDepartmentsThatHaveTask.adapter = adapterDep
        rcvDepartmentsThatHaveTask.layoutManager = LinearLayoutManager(this)

        val temporaryProjList = ProjectManager.projectList
        val adapterProj = ProjectCheckboxAdapter(temporaryProjList)
        rcvProjectsThatHaveTask.adapter = adapterProj
        rcvProjectsThatHaveTask.layoutManager = LinearLayoutManager(this)

        val temporaryEmpList = EmployeeManager.getEmployeeList()
        val adapterEmp = EmployeeAdapter(temporaryEmpList)
        rcvEmployeesThatHaveTask.adapter = adapterEmp
        rcvEmployeesThatHaveTask.layoutManager = LinearLayoutManager(this)

        navigateToAddTaskActivity()
        setUpPopup()
    }
    private fun navigateToAddTaskActivity(){
        val btnCreateTask: Button = findViewById(R.id.btnCreateTask)
        btnCreateTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setUpPopup(){
        val imgBtnAddAny: ImageButton = findViewById(R.id.imgBtnAddAny)
        imgBtnAddAny.setOnClickListener{ view->
            val inflater: LayoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView: View = inflater.inflate(R.layout.add_task_popup, null)

            val popupWindow = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            val btnAddTaskToEmployee2: Button = popupView.findViewById(R.id.btnAddTaskToEmployee2)
            val btnAddTaskToProject: Button = popupView.findViewById(R.id.btnAddTaskToProject)
            val btnAddTaskToDepartment: Button = popupView.findViewById(R.id.btnAddTaskToDepartment)

            btnAddTaskToEmployee2.setOnClickListener{
                popupWindow.dismiss()
                val intent = Intent(this, AddTaskActivity::class.java)
                startActivity(intent)
            }
            btnAddTaskToProject.setOnClickListener{
                popupWindow.dismiss()
                val intent = Intent(this, AddProjectActivity::class.java)
                startActivity(intent)
            }
            btnAddTaskToDepartment.setOnClickListener{
                popupWindow.dismiss()
                val intent = Intent(this, AddDepartmentActivity::class.java)
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
}