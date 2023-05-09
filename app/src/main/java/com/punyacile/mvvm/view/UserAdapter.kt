package com.punyacile.mvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.punyacile.mvvm.databinding.UserItemBinding
import com.punyacile.mvvm.model.getAllUserItem

class UserAdapter(var listUser : List<getAllUserItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNama.text = listUser[position].name
        holder.binding.tvUsername.text = listUser[position].username
        holder.binding.tvAlamat.text = listUser[position].address
        holder.binding.tvUsia.text = listUser[position].age.toString()
        //holder.binding.userPassword.text = listUser[position].password

    }
    override fun getItemCount(): Int {
        return listUser.size
    }
}