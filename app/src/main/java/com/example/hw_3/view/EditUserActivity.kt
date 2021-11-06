package com.example.hw_3.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hw_3.R
import com.example.hw_3.model.User
import com.example.hw_3.view_model.DetailsUserViewModel


class EditUserActivity : AppCompatActivity() {

    private lateinit var vm: DetailsUserViewModel
    private var strTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)

        val btnChange: Button = findViewById(R.id.btn_change)
        vm = ViewModelProvider(this).get(DetailsUserViewModel::class.java)

        initObserver()

        vm.loadDetailsUser(getUserId())

        onClickButChange(btnChange, getUserId())

    }

    private fun initObserver() {
        val editImage: EditText = findViewById(R.id.editLink)
        val editUserName: EditText = findViewById(R.id.editName)
        val editTextStatus: EditText = findViewById(R.id.editStatus)
        val editFollowers: EditText = findViewById(R.id.editFollowers)
        val editFollowing: EditText = findViewById(R.id.editFollowing)
        val editScope: EditText = findViewById(R.id.editSocialScope)
        val editSharemeter: EditText = findViewById(R.id.editSharemeter)
        val editReach: EditText = findViewById(R.id.editReach)
        val editPosts: EditText = findViewById(R.id.editPosts)

        vm.userDetailsLiveData.observe(this, Observer {
            title = it.name
            editImage.setText(it.photoUri)
            editUserName.setText(it.name)
            editTextStatus.setText(it.status)
            editFollowers.setText(it.followers.toString())
            editFollowing.setText(it.following.toString())
            editScope.setText(it.socialScore.toString())
            editSharemeter.setText(it.sharemeter.toString())
            editReach.setText(it.reach)
            editPosts.setText(it.posts)
            strTime = it.time
        })
    }

    private fun getUserId(): Int {
        val arg = intent.extras
        val id: Int = arg?.getInt("user_name")!!.toInt()

        return id
    }

    private fun onClickButChange(view: View, id: Int) {
        val editImage: EditText = findViewById(R.id.editLink)
        val editUserName: EditText = findViewById(R.id.editName)
        val editTextStatus: EditText = findViewById(R.id.editStatus)
        val editFollowers: EditText = findViewById(R.id.editFollowers)
        val editFollowing: EditText = findViewById(R.id.editFollowing)
        val editScope: EditText = findViewById(R.id.editSocialScope)
        val editSharemeter: EditText = findViewById(R.id.editSharemeter)
        val editReach: EditText = findViewById(R.id.editReach)
        val editPosts: EditText = findViewById(R.id.editPosts)
        view.setOnClickListener {

            if (editUserName.text.isEmpty() || editImage.text.isEmpty() || editTextStatus.text.isEmpty() ||
                editScope.text.isEmpty() || editFollowers.text.isEmpty() || editFollowing.text.isEmpty() ||
                editPosts.text.isEmpty() || editReach.text.isEmpty() || editSharemeter.text.isEmpty()
            ) {

                val toast = Toast.makeText(
                    applicationContext,
                    "Please fill in all fields !!",
                    Toast.LENGTH_SHORT
                )
                toast.show()

            } else {
                val user = User(
                    getUserId(),
                    editUserName.text.toString(),
                    strTime,
                    editImage.text.toString(),
                    editTextStatus.text.toString(),
                    editScope.text.toString().toInt(),
                    editFollowers.text.toString().toInt(),
                    editFollowing.text.toString().toInt(),
                    editPosts.text.toString(),
                    editReach.text.toString(),
                    editSharemeter.text.toString().toInt()
                )
                vm.updateUser(user)

                val intent = Intent(this, DetailsUserActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }

        }
    }
}