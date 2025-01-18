package com.example.emsadmin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatMessageAdapter(private val messages: MutableList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        const val TYPE_SENT = 1
        const val TYPE_RECEIVED = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].getStatus()==1) TYPE_SENT else TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_SENT) {
            val view = inflater.inflate(R.layout.chat_item_sent_msg_recycle_view, parent, false)
            SentMessageViewHolder(view)
        } else{
            val view = inflater.inflate(R.layout.chat_item_received_msg_recycle_view, parent, false)
            ReceivedMessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if (holder is SentMessageViewHolder) {
            holder.messageTextView.text = message.getContent()
        } else if (holder is ReceivedMessageViewHolder) {
            (holder as ReceivedMessageViewHolder).messageTextView.text = message.getContent()
        }
    }

    override fun getItemCount(): Int = messages.size

    class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.tvMessageSent)
    }

    class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.tvMessageReceived)
    }
    fun updateMessageList(message: Message) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

}