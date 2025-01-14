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
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DepartmentActivity : AppCompatActivity() {

    private lateinit var tvDepartmentNameDetails: TextView
    private lateinit var btnCreateDepartment: Button
    private lateinit var spinnerDepartments: Spinner
    private lateinit var selectedDepartmentID: String
    private lateinit var employeeListToDisplay: MutableList<Employee>
    private lateinit var projectListToDisplay: MutableList<Project>

    private lateinit var rcvEmployeeInDepartment: RecyclerView
    private lateinit var rcvProjectInDepartment: RecyclerView
    private lateinit var adapterEmp: EmployeeCheckboxAdapter
    private lateinit var adapterProj: ProjectCheckboxAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_department)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navigateToAddDepartmentActivity()
        setUpPopup()

        btnCreateDepartment = findViewById(R.id.btnCreateDepartment)
        btnCreateDepartment.setOnClickListener{
            navigateToAddDepartmentActivity()
        }

        tvDepartmentNameDetails = findViewById(R.id.tvDepartmentNameDetails)

        spinnerDepartments = findViewById(R.id.spinnerDepartments)
        val departmentList = DepartmentManager.getDepartmentList().map{it.getDepartmentName() to it.getDepartmentID()}
        if (departmentList.isEmpty()) {
            Toast.makeText(this, "No departments found", Toast.LENGTH_SHORT).show()
            return
        }
        val departmentNames = departmentList.map{it.first}
        val adapter = ArrayAdapter(this, R.layout.department_spinner_text_only, departmentNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDepartments.adapter = adapter
        spinnerDepartments.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                selectedDepartmentID = departmentList[position].second
                employeeListToDisplay = getEmployeesUnderDepartment(selectedDepartmentID)
                projectListToDisplay = getProjectUnderDepartment(selectedDepartmentID)
                adapterEmp.updateEmployeeList(employeeListToDisplay)
                adapterProj.updateProjectList(projectListToDisplay)
                tvDepartmentNameDetails.text = departmentNames[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
                selectedDepartmentID = ""
                adapterEmp.updateEmployeeList(employeeListToDisplay)
                adapterProj.updateProjectList(projectListToDisplay)
            }
        }
        //getEmployeesUnderDepartment(selectedDepartmentID)
        employeeListToDisplay = mutableListOf()
        rcvEmployeeInDepartment = findViewById(R.id.rcvEmployeeInDepartment)
        adapterEmp = EmployeeCheckboxAdapter(employeeListToDisplay)
        rcvEmployeeInDepartment.adapter = adapterEmp
        rcvEmployeeInDepartment.layoutManager = LinearLayoutManager(this)

        projectListToDisplay = mutableListOf()
        rcvProjectInDepartment = findViewById(R.id.rcvProjectInDepartment)
        adapterProj = ProjectCheckboxAdapter(projectListToDisplay)
        rcvProjectInDepartment.adapter = adapterProj
        rcvProjectInDepartment.layoutManager = LinearLayoutManager(this)
    }
    private fun navigateToAddDepartmentActivity(){
        val btnCreateDepartment: Button = findViewById(R.id.btnCreateDepartment)
        btnCreateDepartment.setOnClickListener{
            val intent = Intent(this, AddDepartmentActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getEmployeesUnderDepartment(departmentID: String): MutableList<Employee>{
        val department = DepartmentManager.searchDepartmentObjByID(departmentID)
        //val employeeListInDepartment = department?.getEmployeeList()?: mutableListOf()
        //val totalEmployeeList = EmployeeManager.getEmployeeList()
        /*
        for(strID in employeeListInDepartment){
            totalEmployeeList.find{it.getEmployeeID() == strID}?.let{
                employeeListToAdd.add(it)
            }
        }*/
        val employeeListToAdd: MutableList<Employee> = EmployeeManager.getEmployeeList()
        return employeeListToAdd
    }
    private fun getProjectUnderDepartment(departmentID: String): MutableList<Project>{
        val department = DepartmentManager.searchDepartmentObjByID(departmentID)
        //val employeeListInDepartment = department?.getEmployeeList()?: mutableListOf()
        //val totalEmployeeList = EmployeeManager.getEmployeeList()
        /*
        for(strID in employeeListInDepartment){
            totalEmployeeList.find{it.getEmployeeID() == strID}?.let{
                employeeListToAdd.add(it)
            }
        }*/
        val projectListToAdd: MutableList<Project> = ProjectManager.projectList
        return projectListToAdd
    }
    private fun setUpPopup(){
        val imgBtnAddAnyInDepartment: ImageButton = findViewById(R.id.imgBtnAddAnyInDepartment)
        imgBtnAddAnyInDepartment.setOnClickListener {
                view->
            val inflater: LayoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView: View = inflater.inflate(R.layout.add_department_popup, null)

            val popupWindow = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            val btnAddTaskToDepartment: Button = popupView.findViewById(R.id.btnAddTaskToEmployee)
            btnAddTaskToDepartment.setOnClickListener {
                popupWindow.dismiss()
                val intent = Intent(this, AddTaskActivity::class.java)
                startActivity(intent)
            }
            val btnAddEmployeeToDepartment: Button = popupView.findViewById(R.id.btnAddTaskToProject)
            btnAddEmployeeToDepartment.setOnClickListener {
                popupWindow.dismiss()
                val intent = Intent(this, AddDepartmentActivity::class.java)
                startActivity(intent)
            }
            val btnAddProjectToDepartment: Button = popupView.findViewById(R.id.btnAddTaskToDepartment)
            btnAddProjectToDepartment.setOnClickListener {
                popupWindow.dismiss()
                val intent = Intent(this, AddProjectActivity::class.java)
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