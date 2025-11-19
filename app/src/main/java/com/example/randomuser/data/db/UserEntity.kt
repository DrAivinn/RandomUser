package com.example.randomuser.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    val firstname: String,
    val lastName: String,
    val age: Int,
    val dateOfBirth: String,
    val phone: String,
    val gender: String,
    val picture: String
)
