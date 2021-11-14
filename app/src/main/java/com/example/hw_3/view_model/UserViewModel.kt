package com.example.hw_3.view_model


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hw_3.model.*
import com.example.hw_3.repository.UserDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(val database: UserDataBaseDao,
                    application: Application) : AndroidViewModel(application) {

    val userData: UserData = UserData()

    private val _userListLiveData = MutableLiveData<List<User>>()
    val userListLiveData: LiveData<List<User>> = _userListLiveData

    private var _userId = MutableLiveData<Int>()
    var userId: LiveData<Int> = _userId

    private suspend fun insert() {
            if (database.checkTablesInDataBase() == null) {
                for (user in userData.userList)
                    database.insert(user)
            }

    }

    private suspend fun load() {
            _userListLiveData.postValue(database.getAllUsers())
    }

    fun insertUserToDB(){
        viewModelScope.launch(Dispatchers.IO) {
            insert()
        }
    }

    fun loadListUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            load()
        }
    }

    fun setUserId(id: Int){
        _userId.value = id
    }


}