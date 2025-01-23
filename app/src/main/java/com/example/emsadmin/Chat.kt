package com.example.emsadmin

class Chat(
    private val isGroup: Boolean,
    private var participantsCount: Int = 2,
    private val participantsList: MutableList<Employee>?=null,//remember to add first person 'default' as the admin for admin version only, and the employee for employee version
    private val messageList: MutableList<Message> = mutableListOf(),
    private var chatID: String=""
){
    init{
        allocateID()
    }
    fun getOtherEmployee(): Employee {
        return this.participantsList?.get(1)!!
    }
    fun getYourself(): Employee{
        return this.participantsList?.get(0)!!
    }
    fun getMessageList(): MutableList<Message>{
        return this.messageList
    }
    fun getChatID(): String{
        return chatID
    }
    private fun allocateID(){
        val length = 10
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        this.chatID = (1..length)
            .map{charset.random()}
            .joinToString("")
    }
}