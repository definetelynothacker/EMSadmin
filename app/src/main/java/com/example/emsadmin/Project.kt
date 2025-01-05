package com.example.emsadmin

class Project(
    private var projectID: String = "",
    private var projectName: String,
    private var projectCompletion: Int = 10,
    private var isComplete: Boolean = false,
    private var employeeIDList: MutableList<String> = mutableListOf(),
    private var startDate: String,
    private var endDate: String,
    private var taskIDList: MutableList<String> = mutableListOf(),
    private var departmentIDList: MutableList<String> = mutableListOf(),

    ){
    init{
        allocateID()
    }
    fun getProjectName(): String{
        return this.projectName
    }
    fun getStatus(): String{
        if(isComplete)
            return "Complete"
        return "Incomplete"
    }
    fun getTaskIDList(): MutableList<String>{
        return taskIDList
    }
    fun getDepartmentIDList(): MutableList<String>{
        return departmentIDList
    }
    fun getEmployeeIDList(): MutableList<String>{
        return employeeIDList
    }
    fun getStartDate(): String{
        return this.startDate
    }
    fun getEndDate(): String{
        return this.endDate
    }
    fun getProgress(): Int{
        return this.projectCompletion
    }
    fun getProjectID(): String{
        return this.projectID
    }
    fun setProjectName(projectName: String){
        this.projectName = projectName
    }
    fun addEmployee(employeeID: String){
        employeeIDList.add(employeeID)
    }
    fun removeEmployee(employeeID: String){
        employeeIDList.remove(employeeID)
    }
    fun completeProject(project: Project){
        project.isComplete = true
    }
    private fun allocateID(){
        val length = 10
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        this.projectID = (1..length)
            .map{charset.random()}
            .joinToString("")
    }
/*
    fun calculateProjectCompletion(project: Project){
        var totalProjectTask = 0
        var taskCompleted = 0
        for(employee in employeeIDList!!){
            for(task in employee.getTaskList()!!){
                if(task.getTaskStatus())
                    taskCompleted++
                totalProjectTask++
            }
        }
        if(totalProjectTask > 0)
            this.projectCompletion = (taskCompleted/totalProjectTask)
        //else do nothing/ don't change projectCompletion
    }*/
}