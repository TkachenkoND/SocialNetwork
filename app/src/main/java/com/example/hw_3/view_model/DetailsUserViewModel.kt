package com.example.hw_3.view_model

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.*
import com.example.hw_3.model.User
import com.example.hw_3.repository.UserDataBase
import com.example.hw_3.repository.UserDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsUserViewModel(
    val database: UserDataBaseDao, application: Application,
) : AndroidViewModel(application) {

    private var _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    val userDetailsLiveData: LiveData<User> = Transformations.switchMap(userId) { id ->
        database.getUser(id)
    }

    private var _navigateToEdit = MutableLiveData<Boolean>()
    var navigateToEdit: LiveData<Boolean> = _navigateToEdit

    private var _navigateBack = MutableLiveData<Boolean>()
    var navigateBack: LiveData<Boolean> = _navigateBack

    private var _navigateRemove = MutableLiveData<Boolean>()
    var navigateRemove: LiveData<Boolean> = _navigateRemove



    private suspend fun update(user: User) {
        database.update(user)
    }

    private suspend fun remove(userId: Int) {
        database.remove(userId)
    }

    fun setUserId(id: Int) {
        _userId.value = id
    }

    private fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            update(user)
        }
    }

    private fun updateRemove(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            remove(userId)
        }
    }

    fun validateAndUpdateUser(
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
            editScope.isEmpty() || editFollowers.isEmpty() || editFollowing.isEmpty() ||
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
            user.userId = userId.value

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

    fun navigateRemove() {
        _navigateRemove.value = true
        updateRemove(userId.value!!)
    }


}