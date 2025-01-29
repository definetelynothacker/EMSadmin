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

class ChatHomeActivity : AppCompatActivity(){

    private lateinit var chatAdapterInst: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){ v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        renderChatList()
        navigateToAddChatActivity()
    }
    private fun renderChatList(){
        val rcvChats: RecyclerView = findViewById(R.id.rcvChats)
        val chatList: MutableList<Chat> = ChatManager.getChatList()
        chatAdapterInst = ChatAdapter(chatList)
        rcvChats.adapter = chatAdapterInst
        rcvChats.layoutManager = LinearLayoutManager(this)
    }
    private fun navigateToAddChatActivity(){
        val imgBtnAddChat: ImageButton = findViewById(R.id.imgBtnAddChat)
        imgBtnAddChat.setOnClickListener{
            val intent = Intent(this, AddEmployeeToChatActivity::class.java)
            startActivity(intent)
        }
    }
}