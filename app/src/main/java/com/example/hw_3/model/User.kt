package com.example.hw_3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(

    @PrimaryKey()
    var userId: Int,

    @ColumnInfo(name = "user_name")
    val name: String,

    @ColumnInfo(name = "time_to_online")
    val time: String,

    @ColumnInfo(name = "photo_uri")
    val photoUri: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "social_score")
    val socialScore: Int,

    @ColumnInfo(name = "followers")
    val followers: Int,

    @ColumnInfo(name = "following")
    val following: Int,

    @ColumnInfo(name = "posts")
    val posts: String,

    @ColumnInfo(name = "reach")
    val reach: String,

    @ColumnInfo(name = "sharemeter")
    val sharemeter: Int
) {


}