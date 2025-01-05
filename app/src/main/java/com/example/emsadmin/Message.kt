package com.example.emsadmin

class Message(
    private val content: String,
    private val senderID: String,
    private val receiverID: String,
    private val timestamp: String,
    private val messageType: Int,//1-text, 2-image, 3-video, 4-audio
    private val isRead: Boolean,
    private val status: Int//1-sent, 2-delivered, 3-failed, 4-deleted
){
    fun getStatus(): Int{
        return this.status
    }
    fun getContent(): String{
        return this.content

    }
}