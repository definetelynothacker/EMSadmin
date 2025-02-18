package com.example.emsadmin

object EmailAccountsManager{
    private var emailAccountsList: MutableList<EmailAccount> = mutableListOf()

    fun getEmailAccountsList(): MutableList<EmailAccount> {
        return emailAccountsList
    }
}