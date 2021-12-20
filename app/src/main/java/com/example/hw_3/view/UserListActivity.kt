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

    private val adapter = UserListAdapter { user ->
        vm.setUserId(user.userId!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = getDataSource()

        val userViewModelFactory = UserViewModelFactory(dataSource, application)

        vm = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]

        vm.insertUserToDB()
        initRecyclerView()
        setButtonListener()
        initNavigationObservers()
        initObserversUserId()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this@UserListActivity)
        vm.userListLiveData.observe(this@UserListActivity, Observer {
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }

    private fun initNavigationObservers() {
        vm.navigateToAdd.observe(this, Observer {
            if (it) {
                val intent = Intent(this, AddNewUserActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun initObserversUserId(){
        vm.userId.observe(this, Observer {
            val intent = Intent(this@UserListActivity, DetailsUserActivity::class.java)
            intent.putExtra("id", vm.userId.value)
            startActivity(intent)
        })
    }

    private fun setButtonListener() {
        btnGoAddActivity.setOnClickListener {
            vm.navigateToAdd()
        }
    }

    private fun getDataSource() = UserDataBase.getDatabase(application).userDataBaseDao()
}