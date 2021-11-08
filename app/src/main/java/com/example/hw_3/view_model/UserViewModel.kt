package com.example.hw_3.view_model


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hw_3.model.*
import com.example.hw_3.repository.UserDataBaseDao
import kotlinx.coroutines.launch


class UserViewModel(val database: UserDataBaseDao,
                    application: Application) : AndroidViewModel(application) {

    private val userData: UserData = UserData()

    private val _userListLiveData = MutableLiveData<List<User>>()
    val userListLiveData: LiveData<List<User>> = _userListLiveData

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    //private val dbHelper = DatabaseHelperImpl(UserDataBase.getDatabase(application))

     fun insertUserToDB() {

        viewModelScope.launch {
            if (database.checkTablesInDataBase() == null) {
                for (user in userData.userList)
                    database.insert(user)
            }
        }

    }

    fun loadListUsers() {

        viewModelScope.launch {
            _userListLiveData.value = database.getAllUsers()
        }
    }

    fun setUserID(id: Int) {
        _userId.value = id
    }


}