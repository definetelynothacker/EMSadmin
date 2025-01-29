package com.example.emsadmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val yourEmailList: MutableList<Email>):
    RecyclerView.Adapter<EmailAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val imgViewEmailImage: ImageButton = itemView.findViewById(R.id.imgViewEmailImage)
        val tvEmailSubject: TextView = itemView.findViewById(R.id.tvEmailSubject)
        val tvEmailBody: TextView = itemView.findViewById(R.id.tvEmailBody)
        val tvEmailTimeSent: TextView = itemView.findViewById(R.id.tvEmailTimeSent)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                val clickedEmail = yourEmailList[position]

                val intent = Intent(itemView.context, EmailActivity::class.java)
                intent.putExtra("email_id", clickedEmail.getEmailID())
                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.email_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailAdapter.ViewHolder, position: Int){
        val email = yourEmailList[position]
        holder.imgViewEmailImage.setImageResource(R.drawable.inbox_icon)
        holder.tvEmailSubject.text = email.getSubject()
        holder.tvEmailBody.text = email.getBody()
        holder.tvEmailTimeSent.text = email.getTimestamp()
    }

    override fun getItemCount(): Int{
        return yourEmailList.size
    }
}