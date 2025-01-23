package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val yourChatList: MutableList<Chat>):
    RecyclerView.Adapter<ChatAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val imgBtnProfileImageReceiverEmployee: ImageButton = itemView.findViewById(R.id.imgBtnProfileImageReceiverEmployee)
        val tvReceiverName: TextView = itemView.findViewById(R.id.tvReceiverName)
        val tvLastSentMessageDate: TextView = itemView.findViewById(R.id.tvLastSentMessageDate)
        val tvLastSentMessageInChat: TextView = itemView.findViewById(R.id.tvLastSentMessageInChat)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedChat = yourChatList[position]

                val intent = Intent(itemView.context, ChatActivity::class.java)
                intent.putExtra("chat_id", clickedChat.getChatID())
                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int){
        val chat = yourChatList[position]
        holder.imgBtnProfileImageReceiverEmployee
        holder.tvReceiverName.text = chat.getOtherEmployee().getFullName()
        val tempDefaults = listOf("hi", "1d")
        holder.tvLastSentMessageInChat.text = tempDefaults[0]
        holder.tvLastSentMessageDate.text = tempDefaults[1]
    }

    override fun getItemCount(): Int{
        return yourChatList.size
    }
}