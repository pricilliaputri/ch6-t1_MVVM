package com.punyacile.mvvm.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.punyacile.mvvm.model.getAllUser
import com.punyacile.mvvm.model.getAllUserItem
import com.punyacile.mvvm.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    var liveDataUser: MutableLiveData<List<getAllUserItem>?> = MutableLiveData()

    fun callApi() {
        ApiClient.instance.getUser().enqueue(object : Callback<List<getAllUserItem>> {
            override fun onResponse(
                call: Call<List<getAllUserItem>>,
                response: Response<List<getAllUserItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataUser.postValue(response.body())
                } else {
                    liveDataUser.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<getAllUserItem>>, t: Throwable) {
                liveDataUser.postValue(null)

            }
        })
    }
}