package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText

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
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        val email = etEmail.text.toString()//admin1 or admin2
        val password = etPassword.text.toString()//admin1 or admin2
        val userID = etConfirmPassword.text.toString()//this is just temporary for me to test our sending messages between two admins
        CurrentUser.updateCurrentUser(email, password, userID)
        val emailAccount = EmailAccount(emailAddress = email)
        EmailAccountsManager.getEmailAccountsList().add(emailAccount)
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
