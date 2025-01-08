package com.example.emsadmin

object EmployeeManager{
    private val employeeList: MutableList<Employee> = mutableListOf(
        Employee("John", "Crabs", employeeID = "EmpID_1", isAdmin = true),
        Employee("Kyle", "Buffet", employeeID = "EmpID_2", isAdmin = true),
        Employee("Sarah", "Sandy", employeeID = "EmpID_3", isAdmin = true),
        Employee("Newton", "Beaufort", employeeID = "EmpID_4", isAdmin = true),
        Employee("Dest", "Hat", employeeID = "EmpID_5", isAdmin = true),
        Employee("JuJu", "Victor", employeeID = "EmpID_6", isAdmin = true),
        Employee("Justin", "Secret", employeeID = "EmpID_7", isAdmin = true),
        Employee("Faring", "Dope", employeeID = "EmpID_8", isAdmin = true)
        )

    fun getEmployeeList(): MutableList<Employee>{
        return employeeList
    }
    fun searchEmployeeObjByID(empID: String): Employee?{
        for(obj in employeeList){
            if(obj.getEmployeeID()==empID)
                return obj
        }
        return null
    }
}