package com.example.hw_3.view_model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw_3.databinding.ListItemBinding
import com.example.hw_3.model.User
import java.util.ArrayList

typealias UserActionListener = (User) -> Unit

class UserAdapter(
    private val actionListener: UserActionListener,

    ) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var userList = mutableListOf<User>()

    class UserViewHolder(
        val binding: ListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = userList[position]

        with(holder.binding) {
            holder.itemView.tag = user
            Glide.with(userPhoto.context)
                .load(user.photoUri)
                .error(com.example.hw_3.R.drawable.ic_launcher_foreground)
                .circleCrop()
                .into(userPhoto)

            userName.text = user.name
            timeToOnline.text = user.time

            holder.itemView.setOnClickListener {
                actionListener(user)
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addUsersToAdapter(users: List<User>) {
        userList.addAll(users)
        notifyDataSetChanged()
    }

}
