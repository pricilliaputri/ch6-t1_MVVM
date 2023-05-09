package com.punyacile.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.mvvm.R
import com.punyacile.mvvm.databinding.ActivityMainBinding
import com.punyacile.mvvm.model.getAllUserItem
import com.punyacile.mvvm.network.ApiClient
import com.punyacile.mvvm.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataUser()

    }

    fun getDataUser() {
        ApiClient.instance.getUser().enqueue(object : Callback<List<getAllUserItem>> {
            override fun onResponse(
                call: Call<List<getAllUserItem>>,
                response: Response<List<getAllUserItem>>
            ) {
                if (response.isSuccessful) {
                    //tanpa viewmodel
//                    binding.rvNews.layoutManager =
//                        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//                        binding.rvNews.adapter = NewsAdapter(response.body()!!)
                    showDataUser()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<getAllUserItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fail", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun showDataUser(){
        val viewModelNews = ViewModelProvider(this@MainActivity).get(UserViewModel::class.java)
        viewModelNews.callApi()
        viewModelNews.liveDataUser.observe(this@MainActivity, Observer {
            if (it != null){
                binding.rvUser.layoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL, false)
                binding.rvUser.adapter = UserAdapter(it)
            }
        })
    }
}