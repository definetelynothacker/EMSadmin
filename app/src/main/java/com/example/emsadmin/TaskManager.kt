package com.example.emsadmin


object TaskManager {
    private var taskList: MutableList<Task> = mutableListOf(
        Task("Task_1", "Design UI", "2025-01-01", "2026-12-11", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_2", "Buy printer", "2025-02-12", "2026-01-03", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_3", "Deliver devices", "2025-05-05", "2026-30-06", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_4", "Buy tablet", "2025-11-12", "2026-06-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_5", "Curser", "2026-31-09", "2026-05-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_6", "Build database", "2025-13-04", "2026-01-03", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_7", "Pickup equipment", "2025-01-06", "2026-07-07", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_8", "train AI model", "2025-01-08", "2026-08-04", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_9", "Create GPTs", "2025-11-02", "2026-13-10", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1")),
        Task("Task_10", "Format machines", "2025-17-02", "2026-22-12", taskStatus = true, departmentIDAssignedTo = mutableListOf("Dept_1"), projectIDAssignedTo = mutableListOf("Proj_1"), employeeIDAssignedTo = mutableListOf("EmpID_1"))
    )

    fun getTaskList(): MutableList<Task>{
        return taskList
    }
    fun searchTaskObjByID(taskID: String): Task?{
        for(obj in taskList){
            if(obj.getTaskID()==taskID)
                return obj
        }
        return null
    }
}