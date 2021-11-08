package com.example.hw_3.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hw_3.model.User

@Dao
interface UserDataBaseDao {
    @Insert
    suspend fun insert(night: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * from users WHERE userId = :key")
    suspend fun getUser(key: Int): User?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun checkTablesInDataBase(): User?
}