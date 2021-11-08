package com.example.hw_3.view_model


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hw_3.model.*
import com.example.hw_3.repository.UserDataBase


class UserViewModel(application: Application) : AndroidViewModel(application) {

    val userData: UserData = UserData()

    private val _userListLiveData = MutableLiveData<List<User>>()
    val userListLiveData: LiveData<List<User>> = _userListLiveData

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    val dataSource = UserDataBase.getDatabase(application).userDataBaseDao()


    fun insertUserToDB() {
        if (dataSource.checkTablesInDataBase() == null) {
            for (user in userData.userList)
                dataSource.insert(user)
        }
    }

    fun loadListUsers() {
        _userListLiveData.value = dataSource.getAllUsers()
    }

    fun setUserID(id: Int) {
        _userId.value = id
    }


}