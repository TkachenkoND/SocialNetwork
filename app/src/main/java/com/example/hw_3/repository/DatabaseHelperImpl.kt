package com.example.hw_3.repository

import com.example.hw_3.model.User

class DatabaseHelperImpl(private val appDatabase: UserDataBase) : DatabaseHelper {

    override suspend fun getAllUsers(): List<User> = appDatabase.userDataBaseDao().getAllUsers()

    override suspend fun insert(user: User) = appDatabase.userDataBaseDao().insert(user)

    override suspend fun update(user: User) = appDatabase.userDataBaseDao().update(user)

    override suspend fun get(key: Int): User? = appDatabase.userDataBaseDao().getUser(key)

    override suspend fun checkTablesInDataBase(): User? = appDatabase.userDataBaseDao().checkTablesInDataBase()


}