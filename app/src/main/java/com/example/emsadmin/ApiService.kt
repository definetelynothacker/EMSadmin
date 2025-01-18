package com.example.emsadmin

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/send")
    fun sendMessage(@Body message: Map<String, String>): Call<Void>

    @GET("/receive/{receiver}")
    fun receiveMessages(@Path("receiver") receiver: String): Call<ResponseBody>
}
