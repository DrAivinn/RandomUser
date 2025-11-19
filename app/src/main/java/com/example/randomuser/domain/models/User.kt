package com.example.randomuser.domain.models


data class User(
    val id: Int = 0,
    val firstname: String,
    val lastName: String,
    val age: Int,
    val dateOfBirth: String,
    val phone: String,
    val gender: String,
    val picture: String
)
