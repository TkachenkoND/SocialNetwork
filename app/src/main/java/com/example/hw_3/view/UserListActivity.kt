package com.example.hw_3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_3.databinding.UserListActivityBinding
import com.example.hw_3.model.User
import com.example.hw_3.repository.UserDataBase
import com.example.hw_3.view_model.UserActionListener
import com.example.hw_3.view_model.UserAdapter
import com.example.hw_3.view_model.UserViewModel
import com.example.hw_3.view_model.UserViewModelFactory

class UserListActivity : AppCompatActivity() {

    private lateinit var vm: UserViewModel

    lateinit var binding: UserListActivityBinding

    private val adapter = UserAdapter(object : UserActionListener {
        override fun goToDetails(user: User) {

            vm.setUserID(user.userId)

            vm.userId.observe(this@UserListActivity, Observer {
                val intent = Intent(this@UserListActivity, DetailsUserActivity::class.java)
                intent.putExtra("id", it)
                startActivity(intent)
            })
        }

    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = UserDataBase.getDatabase(application).userDataBaseDao()
        val viewModelFactory = UserViewModelFactory(dataSource, application)

        vm = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        vm.insertUserToDB()

        vm.loadListUsers()

        init()

    }

    private fun init() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@UserListActivity)

            recyclerView.adapter = adapter

            vm.userListLiveData.observe(this@UserListActivity, Observer {

                adapter.addUsersToAdapter(it)

            })

        }
    }


}