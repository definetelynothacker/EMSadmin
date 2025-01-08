package com.example.emsadmin

object DepartmentManager {
    private val departmentList: MutableList<Department> = mutableListOf(
        Department("Management", "Manager 1", departmentID = "DeptID_1"),
        Department("Accounting", "Manager 1", departmentID = "DeptID_1"),
        Department("IT", "Manager 1", departmentID = "DeptID_1"),
        Department("Technicians", "Manager 1", departmentID = "DeptID_1"),
        Department("Sentries", "Manager 1", departmentID = "DeptID_1"),
        Department("Security", "Manager 1", departmentID = "DeptID_1")
    )
    fun getDepartmentList(): MutableList<Department>{
        return departmentList
    }
    fun searchDepartmentObjByID(depID: String): Department?{
        for(obj in departmentList){
            if(obj.getDepartmentID()==depID)
                return obj
        }
        return null
    }
}