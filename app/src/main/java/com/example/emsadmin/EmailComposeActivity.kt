package com.example.emsadmin

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.util.Properties
import javax.mail.*
import javax.mail.Message
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmailComposeActivity : AppCompatActivity() {


    private lateinit var textInputEditTextFrom: TextInputEditText
    private lateinit var textInputEditTextTo: TextInputEditText
    private lateinit var textInputEditTextSubject: TextInputEditText
    private lateinit var editTextTextMultiLineCompose: EditText
    private lateinit var imgBtnSendEmail: ImageButton

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

        textInputEditTextTo = findViewById(R.id.textInputEditTextTo)
        textInputEditTextFrom = findViewById(R.id.textInputEditTextFrom)
        textInputEditTextSubject = findViewById(R.id.textInputEditTextSubject)
        editTextTextMultiLineCompose = findViewById(R.id.editTextTextMultiLineCompose)
        imgBtnSendEmail = findViewById(R.id.imgBtnSendEmail)

        imgBtnSendEmail.setOnClickListener {
            val sendingFrom: String = textInputEditTextFrom.text.toString()
            val sendingTo: String = textInputEditTextTo.text.toString()
            val subject: String = textInputEditTextSubject.text.toString()
            val password = "I have to figure out a way to sync the email upon signing up like how its done in gmail to be able to not have to ask for the app password"

            val body: String = editTextTextMultiLineCompose.text.toString()
            sendEmail(sendingFrom, sendingTo, password, body)
        }
    }
    private fun navigateBackToPreviousActivity() {
        val imgBtnBackCompose: ImageButton = findViewById(R.id.imgBtnBackCompose)
        imgBtnBackCompose.setOnClickListener{
            val intent = Intent(this, EmailHomeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun sendEmail(sendingFrom: String, recipient: String, yourPassword: String, body: String){
        val properties = Properties().apply{
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val session = Session.getInstance(properties, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(sendingFrom, yourPassword)
                    }
                })

                val message = MimeMessage(session).apply {
                    setFrom(InternetAddress(sendingFrom))
                    setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient))
                    subject = subject
                    setText(body)
                }

                Transport.send(message)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@EmailComposeActivity, "Email sent successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@EmailComposeActivity, "Failed to send email", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}