package com.example.emsadmin

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatActivity : AppCompatActivity() {

    private lateinit var textInputEditText: TextInputEditText
    private lateinit var imgBtnSendMessage: ImageButton
    private lateinit var messageList: MutableList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textInputEditText = findViewById(R.id.textInputEditText)
        imgBtnSendMessage = findViewById(R.id.imgBtnSendMessage)

        messageList = MessageManager.getMessageList()
        val adapter = ChatMessageAdapter(messageList)
        val rcvChatDisplay: RecyclerView = findViewById(R.id.rcvChatDisplay)
        rcvChatDisplay.layoutManager = LinearLayoutManager(this)
        rcvChatDisplay.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("http://:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        imgBtnSendMessage.setOnClickListener{
            val message = textInputEditText.text.toString()
            val otherUser = CurrentUser.getOtherUser().getEmployeeID()
            val senderUser = CurrentUser.getCurrentUser().getEmployeeID()
            val messageToSend = mapOf("receiver" to otherUser, "message" to message)

            val newMessage = Message(message, senderUser, otherUser)
            //messageList.add(newMessage)
            adapter.updateMessageList(newMessage)
            rcvChatDisplay.scrollToPosition(messageList.size - 1)
            textInputEditText.text?.clear()

            apiService.sendMessage(messageToSend).enqueue(object: Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful)
                        receiveMessages(apiService, otherUser, senderUser, otherUser, adapter)
                    else
                        Toast.makeText(this@ChatActivity, "Failed to send message", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    //Handle failure to communicate with server
                    Toast.makeText(this@ChatActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    private fun receiveMessages(apiService: ApiService, receiverId: String, senderID: String, receiverID: String, adapter: ChatMessageAdapter) {
        apiService.receiveMessages(receiverId).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    val messages = response.body()?.string()?:""
                    val newMessage = Message(messages, senderID, receiverID)
                    newMessage.addContent(messages)
                    //messageList.add(newMessage)
                    adapter.updateMessageList(newMessage)
                }
                else
                    Toast.makeText(this@ChatActivity, "Failed to retrieve messages", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
