package com.example.hw_3.view_model

import android.app.Application
import android.widget.EditText
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
        editImage: EditText,
        editUserName: EditText,
        editTextStatus: EditText,
        editFollowers: EditText,
        editFollowing: EditText,
        editScope: EditText,
        editSharemeter: EditText,
        editReach: EditText,
        editPosts: EditText,
        strTime: String,
    ): Boolean {
        if (editUserName.text.isEmpty() || editImage.text.isEmpty() || editTextStatus.text.isEmpty() ||
            editScope.text.isEmpty() || editFollowers.text.isEmpty() || editFollowing.text.isEmpty() ||
            editPosts.text.isEmpty() || editReach.text.isEmpty() || editSharemeter.text.isEmpty()
        ) {
            return false
        } else {
            val user = User(
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
            addNewUserToDb(user)
            return true
        }
    }

}