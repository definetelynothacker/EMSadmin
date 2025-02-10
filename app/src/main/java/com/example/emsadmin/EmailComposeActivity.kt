package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EmailComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_email_compose)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navigateBackToPreviousActivity()
    }
    private fun navigateBackToPreviousActivity() {
        val imgBtnBack: ImageButton = findViewById(R.id.imgBtnBack)
        imgBtnBack.setOnClickListener{
            val intent = Intent(this, EmailHomeActivity::class.java)
            startActivity(intent)
        }
    }
}