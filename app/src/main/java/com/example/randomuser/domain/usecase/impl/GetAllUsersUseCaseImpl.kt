package com.example.randomuser.domain.usecase.impl

import com.example.randomuser.domain.models.User
import com.example.randomuser.domain.repository.RandomUserRepository
import com.example.randomuser.domain.usecase.GetAllUsersUseCase
import jakarta.inject.Inject

class GetAllUsersUseCaseImpl @Inject constructor(val repository: RandomUserRepository) :
    GetAllUsersUseCase {
    override suspend fun invoke(): List<User> {
        return repository.getAllUsers()
    }
}