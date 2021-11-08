package com.example.hw_3.repository

import com.example.hw_3.model.User

interface DatabaseHelper {

    suspend fun getAllUsers(): List<User>

    suspend fun insert(user: User)

    suspend fun update(user: User)

    suspend fun get(key: Int): User?

    suspend fun checkTablesInDataBase(): User?

}