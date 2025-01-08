package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class DepartmentActivity : AppCompatActivity() {

    private lateinit var btnCreateEmployee: Button
    private lateinit var spinnerDepartments: Spinner
    private lateinit var selectedDepartmentID: String
    private lateinit var employeeListAssignedToDepartment: MutableList<Employee>
    private lateinit var employeeListToDisplay: MutableList<Employee>

    private lateinit var rcvEmployeeInDepartment: RecyclerView

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
        navigateToAddEmployeeActivity()

        employeeListAssignedToDepartment = mutableListOf()
        employeeListToDisplay = mutableListOf()

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
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedDepartmentID = departmentList[position].second
                getEmployeesUnderDepartment(selectedDepartmentID)
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
                selectedDepartmentID = ""
            }
        }/*
        getEmployeesUnderDepartment(selectedDepartmentID)
        rcvEmployeeInDepartment = findViewById(R.id.rcvEmployeeInDepartment)
        val adapterEmp = EmployeeAdapter(employeeListToDisplay)
        rcvEmployeeInDepartment.adapter = adapterEmp*/

    }
    private fun navigateToAddDepartmentActivity(){
        val btnCreateDepartment: Button = findViewById(R.id.btnCreateDepartment)
        btnCreateDepartment.setOnClickListener{
            val intent = Intent(this, AddDepartmentActivity::class.java)
            startActivity(intent)
        }
    }
    private fun navigateToAddEmployeeActivity(){
        val  btnCreateEmployee1: Button = findViewById(R.id.btnCreateEmployee1)
        btnCreateEmployee1.setOnClickListener{
            val intent = Intent(this, AddEmployeeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getEmployeesUnderDepartment(departmentID: String){
        val department = DepartmentManager.searchDepartmentObjByID(departmentID)
        val employeeListInDepartment = department?.getEmployeeList()
        val totalEmployeeList = EmployeeManager.getEmployeeList()
        if (employeeListInDepartment != null) {
            for(strID in employeeListInDepartment){
                for(obj in totalEmployeeList){
                    if(obj.getEmployeeID()==strID)
                       employeeListToDisplay.add(obj)
                }
            }
        }
    }
}