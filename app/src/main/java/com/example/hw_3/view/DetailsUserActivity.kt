package com.example.hw_3.view


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_3.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hw_3.repository.UserDataBase
import com.example.hw_3.view_model.details_vm.DetailsUserViewModel
import com.example.hw_3.view_model.details_vm.DetailsUserViewModelFactory
import kotlinx.android.synthetic.main.details_activity.*

class DetailsUserActivity : AppCompatActivity() {

    private lateinit var vm: DetailsUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        val dataSource = UserDataBase.getDatabase(application).userDataBaseDao()

        val detailsUserViewModelFactory = DetailsUserViewModelFactory(dataSource, application)
        vm = ViewModelProvider(this, detailsUserViewModelFactory)[DetailsUserViewModel::class.java]

        vm.setUserId(getUserIdFromUserList())

        initUserDetailsObservers()
        setButtonListener()
        initNavigationObservers()
    }

    private fun initUserDetailsObservers() {
        vm.userDetailsLiveData.observe(this, Observer {
            it?.let { user ->
                title = user.name
                detailsUserName.text = user.name
                detailsUserStatus.text = user.status
                detailsFollowers.text = user.followers.toString()
                detailsFollowing.text = user.following.toString()
                detailsScore.text = user.socialScore.toString()
                detailsSharemeter.text = user.sharemeter.toString()
                detailsReach.text = user.reach
                detailsPosts.text = user.posts

                Glide.with(this)
                    .load(user.photoUri)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(detailsImage)
            }
        })
    }

    private fun initNavigationObservers() {
        vm.navigateToEdit.observe(this, Observer {
            if (it) {
                val intent = Intent(this, EditUserActivity::class.java)
                intent.putExtra("id", vm.userId.value)
                startActivity(intent)
            }
        })

        vm.navigateBack.observe(this, Observer {
            if (it) {
                val intent = Intent(this, UserListActivity::class.java)
                startActivity(intent)
            }
        })

        vm.navigateRemove.observe(this, Observer {
            if (it)
                finish()
        })
    }

    private fun setButtonListener() {

        btnEdit.setOnClickListener {
            vm.navigateToEdit()
        }

        btnBack.setOnClickListener {
            vm.navigateBack()
        }

        btnRemove.setOnClickListener {
            vm.navigateRemove()
        }
    }

    private fun getUserIdFromUserList() = intent.extras?.getInt("id")!!.toInt()
}