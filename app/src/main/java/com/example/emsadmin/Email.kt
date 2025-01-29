package com.example.emsadmin

class Email(
    private val emailID: String = java.util.UUID.randomUUID().toString(), // Unique ID for the email
    private val sender: String,
    private val recipients: List<String>,
    private val ccRecipients: List<String> = emptyList(),
    private val bccRecipients: List<String> = emptyList(),
    private val subject: String,
    private val body: String,
    private val attachments: List<String> = emptyList(),
    private val timestamp: Long = System.currentTimeMillis(),
    private var isRead: Boolean = false,
    private var isSent: Boolean = false,
    private var isDraft: Boolean = false,
    private val priority: String = "Normal"
){
    fun getEmailID(): String {
        return emailID
    }
    fun getSubject(): String{
        return subject
    }
    fun getBody(): String{
        return body
    }
    fun getTimestamp(): String{
        return timestamp.toString()
    }
}
