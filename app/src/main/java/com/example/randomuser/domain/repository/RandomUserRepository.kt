package com.example.randomuser.domain.repository

import com.example.randomuser.domain.models.User

interface RandomUserRepository {
    suspend fun getRandomUser(gender: String, nationality: String): User

    suspend fun getAllUsers(): List<User>

    suspend fun getUserById(id: Int): User
}