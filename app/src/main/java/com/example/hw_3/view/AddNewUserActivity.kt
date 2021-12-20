package com.example.hw_3.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hw_3.databinding.ActivityAddNewUserBinding
import com.example.hw_3.repository.UserDataBase
import com.example.hw_3.view_model.AddNewUserViewModelFactory
import com.example.hw_3.view_model.AddUserViewModel
import kotlinx.android.synthetic.main.activity_add_new_user.*
import kotlinx.android.synthetic.main.activity_add_new_user.editFollowers
import kotlinx.android.synthetic.main.activity_add_new_user.editFollowing
import kotlinx.android.synthetic.main.activity_add_new_user.editLink
import kotlinx.android.synthetic.main.activity_add_new_user.editName
import kotlinx.android.synthetic.main.activity_add_new_user.editPosts
import kotlinx.android.synthetic.main.activity_add_new_user.editReach
import kotlinx.android.synthetic.main.activity_add_new_user.editSharemeter
import kotlinx.android.synthetic.main.activity_add_new_user.editSocialScope
import kotlinx.android.synthetic.main.activity_add_new_user.editStatus

class AddNewUserActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddNewUserBinding
    private lateinit var vm: AddUserViewModel
    private var strTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = UserDataBase.getDatabase(application).userDataBaseDao()

        val addUserViewModelFactory = AddNewUserViewModelFactory(dataSource, application)
        vm = ViewModelProvider(this, addUserViewModelFactory)[AddUserViewModel::class.java]

        setButtonListener()
    }

    private fun setButtonListener() {
        btnAddNewUser.setOnClickListener{
            if (vm.validateAndAddNewUser(
                    editLink.text.toString(),
                    editName.text.toString(),
                    editStatus.text.toString(),
                    editFollowers.text.toString(),
                    editFollowing.text.toString(),
                    editSocialScope.text.toString(),
                    editSharemeter.text.toString(),
                    editReach.text.toString(),
                    editPosts.text.toString(),
                    strTime,
                )
            ) {
                finish()
            } else {
                val toast = Toast.makeText(
                    this,
                    "Please fill in all fields !!", Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }
}