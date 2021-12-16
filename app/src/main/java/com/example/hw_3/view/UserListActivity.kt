package com.example.hw_3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_3.databinding.UserListActivityBinding
import com.example.hw_3.repository.UserDataBase
import com.example.hw_3.view_model.*
import kotlinx.android.synthetic.main.activity_add_new_user.*
import kotlinx.android.synthetic.main.details_activity.*
import kotlinx.android.synthetic.main.user_list_activity.*

class UserListActivity : AppCompatActivity() {

    private lateinit var vm: UserViewModel

    lateinit var binding: UserListActivityBinding

    private val adapter = UserAdapter { user ->
        vm.setUserId(user.userId!!)

        val intent = Intent(this@UserListActivity, DetailsUserActivity::class.java)
        intent.putExtra("id",vm.userId.value)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = getDataSource()

        val userViewModelFactory = UserViewModelFactory(dataSource, application)

        vm = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]

        vm.insertUserToDB()
        //vm.loadListUsers()
        initRecyclerView()

        setButtonListener()
        initNavigationObservers()
    }

    private fun initRecyclerView() {

            recyclerView.layoutManager = LinearLayoutManager(this@UserListActivity)

            recyclerView.adapter = adapter
            vm.userListLiveData.observe(this@UserListActivity, Observer {
                adapter.addUsersToAdapter(it)
            })

    }

    private fun initNavigationObservers() {
        vm.navigateToAdd.observe(this, Observer {
            if (it){
                val intent = Intent(this, AddNewUserActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun setButtonListener() {
        btnGoAddActivity.setOnClickListener {
            vm.navigateToAdd()
        }
    }

    private fun getDataSource() = UserDataBase.getDatabase(application).userDataBaseDao()
}