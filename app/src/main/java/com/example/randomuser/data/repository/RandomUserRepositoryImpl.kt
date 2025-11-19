package com.example.randomuser.data.repository

import com.example.randomuser.data.api.RandomUserApi
import com.example.randomuser.data.db.UserDao
import com.example.randomuser.data.mappers.toDomain
import com.example.randomuser.data.mappers.toEntity
import com.example.randomuser.domain.models.User
import com.example.randomuser.domain.repository.RandomUserRepository

class RandomUserRepositoryImpl(
    private val apiService: RandomUserApi,
    private val dao: UserDao
) : RandomUserRepository {
    override suspend fun getRandomUser(gender: String, nationality: String): User {
        val response = apiService.getRandomUser(gender, nationality).results.random()
        val user = response.toDomain()
        dao.insertUser(user.toEntity())
        return user
    }

    override suspend fun getAllUsers(): List<User> {
        val entities = dao.getAllUsers()
        return entities.map { it.toDomain() }
    }

    override suspend fun getUserById(id: Int): User {
        val user = dao.getUserById(id)
        return user.toDomain()
    }
}