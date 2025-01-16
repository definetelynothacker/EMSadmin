package com.example.emsadmin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(private val messageList: MutableList<Message>):
    RecyclerView.Adapter<MessageAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val messageContent: TextView = itemView.findViewById(R.id.messageContent)
        val senderName: TextView = itemView.findViewById(R.id.senderName)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {
        val message = messageList[position]
        holder.messageContent.text = message.getContent()
        holder.senderName.text = message.getSenderID()
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
    fun updateMessageList(newList: MutableList<Message>){
        messageList.clear()
        val posStart = messageList.size-1
        val posEnd = newList.size-1
        messageList.addAll(newList)
        notifyItemRangeChanged(posStart, posEnd)
    }
}