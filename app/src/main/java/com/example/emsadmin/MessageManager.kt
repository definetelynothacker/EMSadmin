package com.example.emsadmin

object MessageManager {
    private var messageList: MutableList<Message> = mutableListOf()

    fun getMessageList(): MutableList<Message>{
        return this.messageList
    }
}