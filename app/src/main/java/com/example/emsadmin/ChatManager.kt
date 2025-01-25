package com.example.emsadmin

object ChatManager{
    private val emp1: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp2: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp3: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp4: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp5: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp6: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp7: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp8: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp9: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp10: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val emp11: Employee = Employee("Joshua", "Sankar", employeeID = "1212")
    private val empList: MutableList<Employee>  = mutableListOf(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9, emp10, emp11)
    private val chat1: Chat = Chat(isGroup = false, participantsList = empList)
    private val chat2: Chat = Chat(isGroup = false, participantsList = empList)
    private val chat3: Chat = Chat(isGroup = false, participantsList = empList)
    private val chat4: Chat = Chat(isGroup = false, participantsList = empList)
    private val chatList: MutableList<Chat> = mutableListOf(chat1, chat2, chat3, chat4, chat2, chat2, chat2, chat3, chat4, chat2, chat2, chat2, chat3, chat4, chat2, chat2, chat2, chat3, chat4, chat2, chat2, chat2, chat3, chat4, chat2, chat2, chat2, chat3, chat4, chat2, chat2, chat2, chat3, chat4, chat2, chat2)//default value

    fun getChatList(): MutableList<Chat>{
        return chatList
    }
    fun findChat(chatID: String): Chat? {
        val chatList = getChatList()
        for(chat in chatList){
            if(chat.getChatID() == chatID){
                return chat
            }
        }
        return null
    }

}