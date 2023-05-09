package com.punyacile.mvvm.network

import com.punyacile.mvvm.model.getAllUser
import com.punyacile.mvvm.model.getAllUserItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("user")
    fun getUser() : Call<List<getAllUserItem>>


}