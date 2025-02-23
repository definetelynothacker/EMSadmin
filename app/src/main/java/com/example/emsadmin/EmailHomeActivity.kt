package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
        setUpPopup()

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
    private fun setUpPopup(){
        val imgBtnEmailProfileSettings: ImageButton = findViewById(R.id.imgBtnEmailProfileSettings)
        imgBtnEmailProfileSettings.setOnClickListener{ view->
            val inflater: LayoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView: View = inflater.inflate(R.layout.email_accounts_popup, null)

            val popupWindow = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            val rcvEmailAccountsSetUp: RecyclerView = popupView.findViewById(R.id.rcvEmailAccountsSetUp)
            val adapter = EmailAccountAdapter(EmailAccountsManager.getEmailAccountsList())
            rcvEmailAccountsSetUp.adapter = adapter
            rcvEmailAccountsSetUp.layoutManager = LinearLayoutManager(this)

            val tvAddAnotherAccount: TextView = findViewById(R.id.tvAddAnotherAccount)
            tvAddAnotherAccount.setOnClickListener{

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