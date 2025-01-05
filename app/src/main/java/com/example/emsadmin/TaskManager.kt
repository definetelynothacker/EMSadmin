package com.example.emsadmin


object TaskManager {
    private var taskList: MutableList<Task> = mutableListOf(
        Task("Task_1", "Design UI", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_2", "Buy printer", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_3", "Deliver devices", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_4", "Buy tablet", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_5", "Curser", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_6", "Build database", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_7", "Pickup equipment", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_8", "train AI model", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_9", "Create GPTs", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_10", "Format machines", "2025-01-01", "2025-01-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1"))
    )

    fun getTaskList(): MutableList<Task>{
        return taskList
    }
    fun searchTaskObjByID(taskID: String): Boolean{
        for(obj in taskList){
            if(obj.getTaskID()==taskID)
                return true
        }
        return false
    }
}