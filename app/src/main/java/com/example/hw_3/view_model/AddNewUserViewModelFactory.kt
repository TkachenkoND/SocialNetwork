package com.example.hw_3.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw_3.repository.UserDataBaseDao

class AddNewUserViewModelFactory(
    private val dataSource: UserDataBaseDao,
    private val application: Application,
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddUserViewModel::class.java)) {
            return AddUserViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}