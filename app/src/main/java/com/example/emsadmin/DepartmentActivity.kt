package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DepartmentActivity : AppCompatActivity() {

    private lateinit var btnCreateEmployee: Button

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
    }
    private fun navigateToAddDepartmentActivity(){
        val btnCreateDepartment: Button = findViewById(R.id.btnCreateDepartment)
        btnCreateDepartment.setOnClickListener{
            val intent = Intent(this, AddDepartmentActivity::class.java)
            startActivity(intent)
        }
    }
    private fun navigateToAddEmployeeActivity(){
        val btnCreateEmployee1: Button = findViewById(R.id.btnCreateEmployee1)
        btnCreateEmployee1.setOnClickListener{
            val intent = Intent(this, AddEmployeeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun createEmployeeAssignedToDepartment(){

    }
}