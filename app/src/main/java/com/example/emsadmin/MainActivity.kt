package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //if(fillFields())
            navigateToHomeActivity()
    }
    private fun fillFields(): Boolean {
        val etEmail: TextInputEditText = findViewById(R.id.etEmail)
        val etPassword: TextInputEditText = findViewById(R.id.etPassword)
        val etConfirmPassword: TextInputEditText = findViewById(R.id.etConfirmPassword)
        val etCompanyName: TextInputEditText = findViewById(R.id.etCompanyName)
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.error = "Field cannot be empty"
            return false
        }
        else if (TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.error = "Field cannot be empty"
            return false
        }
        else if(TextUtils.isEmpty(etConfirmPassword.getText().toString())){
            etConfirmPassword.error = "Field cannot be empty"
            return false
        }
        else if(TextUtils.isEmpty(etCompanyName.getText().toString())){
            etCompanyName.error = "Field cannot be empty"
            return false
        }
        return true
    }
    private fun navigateToHomeActivity(){
        val btnCreateAccount: Button = findViewById(R.id.btnCreateAccount)
        btnCreateAccount.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
