package com.example.hw_3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(

    val name: String,

    val time: String,

    val photoUri: String,

    val status: String,

    val socialScore: Int,

    val followers: Int,

    val following: Int,

    val posts: String,

    val reach: String,

    val sharemeter: Int
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int? = null
}