package com.example.emsadmin

class EmailAccount(
                    private var accountName: String = "null",
                    private var emailAddress: String,
                    private var accountType: String= "null",//POP3, //IMAP
                    private var password: String= "null",
                    private var server: String= "null"
    ){
    fun getAccountName(): String{return accountName}
    fun getEmailAddress(): String{return emailAddress}
}