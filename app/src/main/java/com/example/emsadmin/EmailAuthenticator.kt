package com.example.emsadmin

import java.util.Properties
import javax.mail.Session
import javax.mail.Store

object EmailAuthenticator {
    fun checkEmailConnection(
        email: String,
        password: String,
        server: String,
        type: String//1 - POP3     2 - IMAP
    ): Boolean{
        val properties = Properties()

        when(type.uppercase()){
            "IMAP" -> {
                properties["mail.store.protocol"] = "imaps"
                properties["mail.imap.host"] = server
                properties["mail.imap.port"] = "993"
            }
            "POP3" -> {
                properties["mail.store.protocol"] = "pop3s"
                properties["mail.pop3.host"] = server
                properties["mail.pop3.port"] = "995"
            }
            else -> return false
        }
        return try{
            val session = Session.getInstance(properties, null)
            val store: Store = session.getStore()
            store.connect(email, password)
            store.close()
            true
        }
        catch(e: Exception){
            e.printStackTrace()
            false
        }
    }
}