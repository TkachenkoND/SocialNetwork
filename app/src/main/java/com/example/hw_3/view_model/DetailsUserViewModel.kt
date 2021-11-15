package com.example.hw_3.view_model

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.hw_3.model.User
import com.example.hw_3.repository.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsUserViewModel(application: Application) : AndroidViewModel(application) {


    private val _userDetailsLiveData = MutableLiveData<User>()
    val userDetailsLiveData: LiveData<User> = _userDetailsLiveData

    private var _userId = MutableLiveData<Int>()

    private var _navigateToEdit = MutableLiveData<Boolean>()
    var navigateToEdit: LiveData<Boolean> = _navigateToEdit

    private var _navigateBack = MutableLiveData<Boolean>()
    var navigateBack: LiveData<Boolean> = _navigateBack

    val dataSource = UserDataBase.getDatabase(application).userDataBaseDao()

    private suspend fun update(user: User) {
        dataSource.update(user)
    }

    fun setUserId(id: Int) {
        _userId.value = id
    }

    fun loadDetailsUser() {
        dataSource.getUser(_userId.value!!).observeForever(Observer {
            _userDetailsLiveData.postValue(it)
        })
    }

    private fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            update(user)
        }
    }


    fun validate(
        editImage: EditText,
        editUserName: EditText,
        editTextStatus: EditText,
        editFollowers: EditText,
        editFollowing: EditText,
        editScope: EditText,
        editSharemeter: EditText,
        editReach: EditText,
        editPosts: EditText,
        id: Int,
        strTime: String,
    ): Boolean {

        if (editUserName.text.isEmpty() || editImage.text.isEmpty() || editTextStatus.text.isEmpty() ||
            editScope.text.isEmpty() || editFollowers.text.isEmpty() || editFollowing.text.isEmpty() ||
            editPosts.text.isEmpty() || editReach.text.isEmpty() || editSharemeter.text.isEmpty()
        ) {

            return false
        } else {
            val user = User(
                id,
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
            updateUser(user)
            return true
        }
    }

    fun navigateToEdit() {
        _navigateToEdit.value = true
    }

    fun navigateBack() {
        _navigateBack.value = true
    }


}