package com.example.hw_3.view



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hw_3.R
import com.example.hw_3.view_model.DetailsUserViewModel
import kotlinx.android.synthetic.main.edit_profile_activity.*


class EditUserActivity : AppCompatActivity() {

    private lateinit var vm: DetailsUserViewModel
    private var strTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)

        vm = ViewModelProvider(this).get(DetailsUserViewModel::class.java)

        loadDetailsUser()

        initUserDetailsObservers()

        onClickButChange(btn_change, getUserIdFormDetailsUser())

    }

    private fun initUserDetailsObservers() {

        vm.userDetailsLiveData.observe(this, Observer {
            title = it.name
            editLink.setText(it.photoUri)
            editName.setText(it.name)
            editStatus.setText(it.status)
            editFollowers.setText(it.followers.toString())
            editFollowing.setText(it.following.toString())
            editSocialScope.setText(it.socialScore.toString())
            editSharemeter.setText(it.sharemeter.toString())
            editReach.setText(it.reach)
            editPosts.setText(it.posts)
            strTime = it.time
        })

    }

    private fun loadDetailsUser() {

        vm.setUserId(getUserIdFormDetailsUser())

        vm.userId.observe(this, Observer {
            vm.loadDetailsUser(it)

        })


    }

    private fun onClickButChange(view: View, id: Int) {

        view.setOnClickListener {

            if (vm.validate(
                    editLink,
                    editName,
                    editStatus,
                    editFollowers,
                    editFollowing,
                    editSocialScope,
                    editSharemeter,
                    editReach,
                    editPosts,
                    id,
                    strTime,
                )) {
                //val intent = Intent(this, DetailsUserActivity::class.java)
                //startActivity(intent)
                finish()
            }
            else{
                val toast = Toast.makeText(
                    this,
                    "Please fill in all fields !!", Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }

    private fun getUserIdFormDetailsUser() = intent.extras?.getInt("id")!!.toInt()
}