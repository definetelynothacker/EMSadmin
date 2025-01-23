package com.example.emsadmin

class Message(
    private var content: String,
    private val senderID: String="",//emp id
    private val receiverID: String="",//emp id
    private val timestamp: String="",
    private val messageType: Int=1,//1-text, 2-image, 3-video, 4-audio
    private val isRead: Boolean=false,
    private val status: Int=1,//1-sent, 2-delivered, 3-failed, 4-deleted,
    private var messageID: String=""
){
    init{
        allocateID()
    }
    fun getStatus(): Int{
        return this.status
    }
    fun getContent(): String{
        return this.content
    }
    fun addContent(content: String){
        this.content = content
    }
    fun getSenderID(): String{
        return this.senderID
    }
    private fun allocateID(){
        val length = 10
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        this.messageID = (1..length)
            .map{charset.random()}
            .joinToString("")
    }
}