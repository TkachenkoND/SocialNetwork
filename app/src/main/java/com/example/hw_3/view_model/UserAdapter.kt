package com.example.hw_3.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw_3.databinding.ListItemBinding
import com.example.hw_3.model.User
import java.util.ArrayList

class UserAdapter(
    private val actionListener: UserActionListener

) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), View.OnClickListener {

    private var userList = ArrayList<User>()

    class UserViewHolder(
        val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

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
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun addUsersToAdapter(users: List<User>) {
        userList.addAll(users)
        notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        val user: User = v.tag as User

        actionListener.goToDetails(user)

    }

}

interface UserActionListener {
    fun goToDetails(user: User)
}