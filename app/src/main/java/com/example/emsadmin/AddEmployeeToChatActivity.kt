package com.example.emsadmin

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AddEmployeeToChatActivity : AppCompatActivity() {

    private lateinit var rcvChatEmployee: RecyclerView
    private lateinit var imgBtnGoBack: ImageButton
    private lateinit var imgBtnSearchChatToAdd: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_employee_to_chat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){ v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        rcvChatEmployee = findViewById(R.id.rcvChatEmployees)
        imgBtnGoBack = findViewById(R.id.imgBtnGoBack)
        imgBtnSearchChatToAdd = findViewById(R.id.imgBtnSearchChatToAdd)

        val empList = EmployeeManager.getEmployeeList()
        val selectedEmp: MutableList<Employee> = mutableListOf()
        val empRemList = empList.subtract(selectedEmp.toSet())
        val empNewList = empRemList.toMutableList()
        val chat1: Chat = Chat(isGroup = false, participantsList = empNewList)
        val chatList = ChatManager.getChatList()
        chatList.add(0, chat1)
        val adapter = AddEmployeeChatAdapter(empRemList.toMutableList())
        rcvChatEmployee.adapter = adapter
        rcvChatEmployee.layoutManager = LinearLayoutManager(this)
    }
}