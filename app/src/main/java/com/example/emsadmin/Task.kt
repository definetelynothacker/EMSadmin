package com.example.emsadmin

class Task(
    private var taskID:String = "",
    private var taskName: String,
    private var taskStartDate: String,
    private var taskEndDate: String,
    private var taskStatus: Boolean = false,
    private var departmentIDAssignedTo: MutableList<String>?=null,
    private var projectIDAssignedTo: MutableList<String>?=null,
    private var employeeIDAssignedTo: MutableList<String>?=null,
    private var periodStatus: Int = 1, //1 for temporary, 2 for recurring
    ){

    init{
        allocateID()
    }

    fun getTaskID(): String{
        return this.taskID
    }
    fun getTaskName(): String{
        return this.taskName
    }
    fun getTaskStartDate(): String{
        return this.taskStartDate
    }
    fun getTaskEndDate(): String{
        return this.taskEndDate
    }
    fun getTaskStatus(): String{
        if(taskStatus)
            return "Complete"
        return "Incomplete"
    }
    fun getPeriodStatus(): Int{
        return this.periodStatus
    }
    fun assignToDepartment(departmentID: String){
        departmentIDAssignedTo?.add(departmentID)
    }
    fun assignToProject(projectID: String){
        departmentIDAssignedTo?.add(projectID)
    }
    fun assignToEmployee(employeeID: String){
        employeeIDAssignedTo?.add(employeeID)
    }
    private fun allocateID(){
        val length = 10
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        this.taskID = (1..length)
            .map{charset.random()}
            .joinToString("")
    }
}