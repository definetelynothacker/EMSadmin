package com.example.emsadmin

object CurrentUser {
    private val emp1: Employee = Employee("empty", "empty", employeeID = "null")
    private val emp2: Employee = Employee("empty", "empty", employeeID = "null")
    fun updateCurrentUser(fName: String, lName: String, ID: String){
        if(fName=="admin1"){//if current user is admin 1 then other user (to send msg to) is admin 2
            emp2.changeFName("admin2")
            emp2.changeLName("admin2")
            emp2.changeID("admin2")
        }
        else{//if current user is admin 2 then other user (to send msg to) is admin 1
            emp1.changeFName("admin1")
            emp1.changeLName("admin1")
            emp1.changeID("admin1")
        }
        emp1.changeFName(fName)
        emp1.changeLName(lName)
        emp1.changeID(ID)
        //when user logs in as admin for testing purposes the default account will be set to either or of the two admin accounts
    }
    fun getCurrentUser(): Employee{
        return this.emp1
    }
    fun getOtherUser(): Employee{
        return this.emp2
    }
}