package com.example.emsadmin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAccountAdapter(private val yourEmailAccountsList: MutableList<EmailAccount>):
    RecyclerView.Adapter<EmailAccountAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val imageViewEmailAccountImage: ImageView = itemView.findViewById(R.id.imageViewEmailAccountImage)
        val tvEmailAccountName: TextView = itemView.findViewById(R.id.tvEmailAccountName)
        val tvEmailAddress: TextView = itemView.findViewById(R.id.tvEmailAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailAccountAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.email_account_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailAccountAdapter.ViewHolder, position: Int){
        val emailAccount = yourEmailAccountsList[position]
        holder.imageViewEmailAccountImage.setImageResource(R.drawable.employee_default_logo)
        holder.tvEmailAccountName.text = emailAccount.getAccountName()
        holder.tvEmailAddress.text = emailAccount.getEmailAddress()
    }

    override fun getItemCount(): Int{
        return yourEmailAccountsList.size
    }
}