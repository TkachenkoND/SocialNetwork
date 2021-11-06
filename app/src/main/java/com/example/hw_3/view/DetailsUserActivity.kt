package com.example.hw_3.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hw_3.R
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.hw_3.view_model.DetailsUserViewModel
import kotlinx.android.synthetic.main.details_activity.*
import kotlinx.android.synthetic.main.edit_profile_activity.*


class DetailsUserActivity : AppCompatActivity(){

    private lateinit var vm: DetailsUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        vm = ViewModelProvider(this).get(DetailsUserViewModel::class.java)

        vm.loadDetailsUser(getUserIdFromUserList())

        initUserDetailsObservers()

        initUserIdObservers()


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

            setButtonListener(btnEdit,it.userId)

        })

    }

    private fun initUserIdObservers(){

        vm.userId.observe(this, Observer {
            val intent = Intent(this, EditUserActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        })
    }

    private fun setButtonListener(view: View?, id: Int = 0 ) {
        view!!.setOnClickListener{
            when(view){
                btnEdit -> vm.setUserIdInLiveData(id)
                btnBack -> onClickBtnBack()
            }

        }
    }

    private fun getUserIdFromUserList() = intent.extras?.getInt("id")!!.toInt()

    private fun onClickBtnBack() {

        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }



}