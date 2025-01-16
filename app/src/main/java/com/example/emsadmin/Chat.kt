package com.example.emsadmin

class Chat(
    private val isGroup: Boolean,
    private var participantsCount: Int = 2,
    private val participantsList: MutableList<Employee>?=null,//remember to add first person 'default' as the admin for admin version only, and the employee for employee version
    private val messageList: MutableList<Message> = mutableListOf()
){
    fun getSender(): Employee {
        return this.participantsList?.get(1)!!
    }
    fun getReceiver(): Employee{
        return this.participantsList?.get(0)!!
    }
    fun getMessageList(): MutableList<Message>{
        return this.messageList
    }
}