package com.example.emsadmin

class Department(
    private var departmentName: String,
    private var departmentManager: String,//convert to Employee object eventually
    private var employeeIDList: MutableList<String> = mutableListOf(),
    private var projectIDList: MutableList<String> = mutableListOf(),
    private var taskIDList: MutableList<String> = mutableListOf(),
    private var departmentID: String=""
    ){
    init{
        allocateID()
    }

    fun setDepartmentName(departmentName: String){
        this.departmentName = departmentName
    }
    fun setDepartmentManager(departmentManager: String){
        this.departmentManager = departmentManager
    }
    fun getDepartmentName(): String{
        return this.departmentName
    }
    fun getDepartmentManager(): String{
        return this.departmentManager
    }
    fun getDepartmentID(): String{
        return this.departmentID
    }
    fun getEmployeeList(): MutableList<String>{
        return employeeIDList
    }
    fun getProjectList(): MutableList<String>{
        return projectIDList
    }
    fun getTaskList(): MutableList<String>{
        return taskIDList
    }
    private fun allocateID(){
        val length = 10
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        this.departmentID = (1..length)
            .map{charset.random()}
            .joinToString("")
    }
}