package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class EmailHomeActivity : AppCompatActivity(){

    private lateinit var rcvEmailsInbox: RecyclerView
    private lateinit var textInputEditTextSearchEmail: TextInputEditText
    private lateinit var imgBtnEmailProfileSettings: ImageButton

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_email_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navigateToEmailComposeActivity()

        rcvEmailsInbox = findViewById(R.id.rcvEmailsInbox)
        textInputEditTextSearchEmail = findViewById(R.id.textInputEditTextSearchEmail)
        imgBtnEmailProfileSettings = findViewById(R.id.imgBtnEmailProfileSettings)

        val emailList = EmailManager.getEmailList()
        val adapter = EmailAdapter(emailList)
        rcvEmailsInbox.adapter = adapter
        rcvEmailsInbox.layoutManager = LinearLayoutManager(this)

    }
    private fun navigateToEmailComposeActivity(){
        val compose: ImageButton = findViewById(R.id.imgBtnCompose)
        compose.setOnClickListener{
            val intent = Intent(this, EmailComposeActivity::class.java)
            startActivity(intent)
        }
    }
}