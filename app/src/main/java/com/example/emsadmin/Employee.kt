package com.example.emsadmin

class Employee(
    private var employeeFName: String,
    private var employeeLName: String,
    private var taskList: MutableList<Task>? = null,
    private var employeeID: String = "",
    private var isAdmin: Boolean = true,
    private var departmentIDList: MutableList<String> = mutableListOf(),
    private var projectIDList: MutableList<String> = mutableListOf(),
    private var taskIDList: MutableList<String> = mutableListOf(),
    private var chatListSent: MutableList<Chat> = mutableListOf()
){

    init{
        allocateID()
    }
    fun changeFName(employeeFName: String){
        this.employeeFName = employeeFName
    }
    fun changeLName(employeeLName: String){
        this.employeeLName = employeeLName
    }
    fun changeID(employeeID: String){
        this.employeeID = employeeID
    }
    fun addTask(task: Task){
        taskList?.add(task)
    }
    fun getFullName(): String{
        val fullName = this.employeeFName + " " + this.employeeLName
        return fullName
    }
    fun getTaskList(): MutableList<Task>? {
        return taskList
    }
    fun getEmployeeID(): String{
        return this.employeeID
    }
    fun getChatList(): MutableList<Chat>{
        return this.chatListSent
    }
    fun getMostRecentMessageFromChatList(): Message {
        val chat = chatListSent.firstOrNull() ?: return Message("No message sent")
        return chat.getMessageList().lastOrNull() ?: Message("No message sent")
    }
    fun assignToDepartment(departmentID: String){
        departmentIDList.add(departmentID)
    }
    fun assignToProject(projectID: String){
        projectIDList.add(projectID)
    }
    private fun allocateID(){
        val length = 10
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        this.employeeID = (1..length)
            .map{charset.random()}
            .joinToString("")
    }
}