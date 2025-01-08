package com.example.emsadmin

object ProjectManager {
    //val projectList: MutableList<Project> = mutableListOf()
    val projectList: MutableList<Project> = mutableListOf<Project>(
        Project(
            projectID = "P001",
            projectName = "Website Redesign",
            projectCompletion = 40,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-01-01",
            endDate = "2024-06-30",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D001")
        ),
        Project(
            projectID = "P002",
            projectName = "Mobile App Development",
            projectCompletion = 60,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-02-01",
            endDate = "2024-08-15",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D002", "D003")
        ),
        Project(
            projectID = "P003",
            projectName = "Cloud Migration",
            projectCompletion = 20,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-03-15",
            endDate = "2024-12-31",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D004")
        ),
        Project(
            projectID = "P004",
            projectName = "AI Model Training",
            projectCompletion = 50,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-01-10",
            endDate = "2024-11-30",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D005")
        ),
        Project(
            projectID = "P005",
            projectName = "Data Analysis Dashboard",
            projectCompletion = 80,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-04-01",
            endDate = "2024-07-31",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D006")
        ),
        Project(
            projectID = "P006",
            projectName = "Marketing Campaign Launch",
            projectCompletion = 25,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-05-01",
            endDate = "2024-12-15",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D007")
        ),
        Project(
            projectID = "P007",
            projectName = "Cybersecurity Upgrade",
            projectCompletion = 35,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-06-01",
            endDate = "2024-10-31",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D008")
        ),
        Project(
            projectID = "P008",
            projectName = "Employee Onboarding Portal",
            projectCompletion = 15,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-07-01",
            endDate = "2024-09-30",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D009")
        ),
        Project(
            projectID = "P009",
            projectName = "Customer Feedback System",
            projectCompletion = 45,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-08-01",
            endDate = "2024-12-01",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D010")
        ),
        Project(
            projectID = "P010",
            projectName = "IT Infrastructure Upgrade",
            projectCompletion = 70,
            isComplete = true,
            employeeIDList = mutableListOf(),
            startDate = "2023-11-01",
            endDate = "2024-02-15",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D011")
        ),
        Project(
            projectID = "P011",
            projectName = "E-commerce Integration",
            projectCompletion = 30,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-03-01",
            endDate = "2024-10-15",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D012")
        ),
        Project(
            projectID = "P012",
            projectName = "Customer Loyalty Program",
            projectCompletion = 55,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-01-15",
            endDate = "2024-08-01",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D013")
        ),
        Project(
            projectID = "P013",
            projectName = "Supply Chain Optimization",
            projectCompletion = 65,
            isComplete = true,
            employeeIDList = mutableListOf(),
            startDate = "2023-12-01",
            endDate = "2024-06-01",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D014")
        ),
        Project(
            projectID = "P014",
            projectName = "Social Media Strategy",
            projectCompletion = 20,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-02-01",
            endDate = "2024-09-30",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D015")
        ),
        Project(
            projectID = "P015",
            projectName = "Office Renovation",
            projectCompletion = 10,
            isComplete = false,
            employeeIDList = mutableListOf(),
            startDate = "2024-03-15",
            endDate = "2024-11-30",
            taskIDList = mutableListOf(),
            departmentIDList = mutableListOf("D016")
        )
    )

    fun searchProjectObjByID(projID: String): Project?{
        for(obj in projectList){
            if(obj.getProjectID()==projID)
                return obj
        }
        return null
    }
}