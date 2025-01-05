package com.example.emsadmin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        renderChatList()
    }
    private fun renderChatList(){
        val rcvChats: RecyclerView = findViewById(R.id.rcvChats)
        val chatList: MutableList<Chat> = ChatManager.getChatList()
        val chatAdapterInst = ChatAdapter(chatList)
        rcvChats.adapter = chatAdapterInst
        rcvChats.layoutManager = LinearLayoutManager(this)
    }
}