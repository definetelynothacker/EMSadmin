package com.example.emsadmin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.coroutines.*

class SetUpEmailActivity : AppCompatActivity() {

    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextServer: EditText
    private lateinit var radioButtonIMAP: RadioButton
    private lateinit var radioButtonPOP3: RadioButton
    private lateinit var btnConnect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_set_up_email)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextServer = findViewById(R.id.editTextServer)

        radioButtonIMAP = findViewById(R.id.radioButtonIMAP)
        radioButtonPOP3 = findViewById(R.id.radioButtonPOP3)
        btnConnect = findViewById(R.id.btnConnect)

        btnConnect.setOnClickListener {
            val email = editTextEmailAddress.text.toString()
            val password = editTextPassword.text.toString()
            val server = editTextServer.text.toString()
            val type = if(radioButtonIMAP.isChecked) "IMAP" else "POP3"


        }
    }
}