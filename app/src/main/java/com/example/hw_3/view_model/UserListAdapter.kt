package com.example.hw_3.view_model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw_3.R
import com.example.hw_3.databinding.ListItemBinding
import com.example.hw_3.model.User

private typealias UserClickListener = (User) -> Unit

class UserListAdapter(private val clickListener: UserClickListener) :
    ListAdapter<User, UserListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(itemView) {
            with(binding) {
                itemView.tag = user
                Glide.with(userPhoto.context)
                    .load(user.photoUri)
                    .error(R.drawable.ic_launcher_foreground)
                    .circleCrop()
                    .into(userPhoto)

                userName.text = user.name
                timeToOnline.text = user.time

            }
            setOnClickListener {
                clickListener(user)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
