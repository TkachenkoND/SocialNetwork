package com.example.hw_3.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hw_3.model.User

@Dao
interface UserDataBaseDao {
    @Insert
     fun insert(night: User)

    @Update
     fun update(user: User)

    @Query("SELECT * from users WHERE userId = :key")
     fun getUser(key: Int): User?

    @Query("SELECT * FROM users")
     fun getAllUsers(): List<User>

    @Query("SELECT * FROM users LIMIT 1")
     fun checkTablesInDataBase(): User?
}