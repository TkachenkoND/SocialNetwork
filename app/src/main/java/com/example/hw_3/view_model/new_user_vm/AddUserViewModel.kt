package com.example.hw_3.view_model.new_user_vm

import android.app.Application
import androidx.lifecycle.*
import com.example.hw_3.model.User
import com.example.hw_3.repository.UserDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddUserViewModel(
    val database: UserDataBaseDao,
    application: Application, ) : AndroidViewModel(application) {

    private suspend fun addNewUser(user: User) {
        database.insert(user)
    }

    fun addNewUserToDb(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewUser(user)
        }
    }

    fun validateAndAddNewUser(
        editImage: String,
        editUserName: String,
        editTextStatus: String,
        editFollowers: String,
        editFollowing: String,
        editScope: String,
        editSharemeter: String,
        editReach: String,
        editPosts: String,
        strTime: String,
    ): Boolean {
        if (editUserName.isEmpty() || editImage.isEmpty() || editTextStatus.isEmpty() ||
            editScope.isEmpty() || editFollowers.isEmpty() || editFollowing.isEmpty()  ||
            editPosts.isEmpty() || editReach.isEmpty() || editSharemeter.isEmpty()
        ) {
            return false
        } else {
            val user = User(
                editUserName,
                strTime,
                editImage,
                editTextStatus,
                editScope.toInt(),
                editFollowers.toInt(),
                editFollowing.toInt(),
                editPosts,
                editReach,
                editSharemeter.toInt()
            )
            addNewUserToDb(user)
            return true
        }
    }
}