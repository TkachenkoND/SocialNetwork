package com.example.hw_3.view


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_3.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hw_3.repository.UserDataBase
import com.example.hw_3.view_model.DetailsUserViewModel
import com.example.hw_3.view_model.DetailsUserViewModelFactory
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
            title = it.name
            detailsUserName.text = it.name
            detailsUserStatus.text = it.status
            detailsFollowers.text = it.followers.toString()
            detailsFollowing.text = it.following.toString()
            detailsScore.text = it.socialScore.toString()
            detailsSharemeter.text = it.sharemeter.toString()
            detailsReach.text = it.reach
            detailsPosts.text = it.posts

            Glide.with(this)
                .load(it.photoUri)
                .error(R.drawable.ic_launcher_foreground)
                .into(detailsImage)
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
    }

    private fun setButtonListener() {

        btnEdit.setOnClickListener {
            vm.navigateToEdit()
        }

        btnBack.setOnClickListener {
            vm.navigateBack()
        }
    }

    private fun getUserIdFromUserList() = intent.extras?.getInt("id")!!.toInt()
}