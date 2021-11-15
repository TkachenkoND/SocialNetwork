package com.example.hw_3.view_model

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.*
import com.example.hw_3.model.User
import com.example.hw_3.repository.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsUserViewModel(application: Application) : AndroidViewModel(application) {

    private var _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    val userDetailsLiveData: LiveData<User> = Transformations.switchMap(userId) { id ->
        dataSource.getUser(id)
    }

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

    private fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            update(user)
        }
    }

    fun validateAndUpdateUser(
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
                _userId.value!!,
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