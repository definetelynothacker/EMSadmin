package com.example.emsadmin

object EmailManager {
    private var emailList: MutableList<Email> = mutableListOf(
        Email(
            sender = "john.doe@example.com",
            recipients = listOf("jane.smith@example.com"),
            subject = "Meeting Reminder",
            body = "Hi Jane,\n\nJust a reminder about the meeting scheduled for tomorrow at 10 AM.\n\nBest regards,\nJohn",
            isRead = true
        ),
        Email(
            sender = "team@example.com",
            recipients = listOf("user1@example.com", "user2@example.com"),
            subject = "Weekly Update",
            body = "Hello Team,\n\nHere's the weekly update. Please review the attached document.\n\nBest,\nTeam",
            attachments = listOf("weekly_update.pdf"),
            priority = "High"
        ),
        Email(
            sender = "noreply@service.com",
            recipients = listOf("user@example.com"),
            subject = "Account Verification",
            body = "Dear User,\n\nPlease verify your account by clicking on the link below:\n\nhttp://example.com/verify\n\nThank you.",
            isSent = true
        ),
        Email(
            sender = "support@company.com",
            recipients = listOf("customer@example.com"),
            subject = "Support Ticket Update",
            body = "Hello,\n\nYour support ticket has been updated. Please log in to your account for more details.\n\nRegards,\nSupport Team",
            isRead = false
        )
    )

    fun getEmailList(): MutableList<Email> {
        return emailList
    }
    fun searchEmailObjByID(emailID: String): Email?{
        for(obj in emailList){
            if(obj.getEmailID()==emailID)
                return obj
        }
        return null
    }
}