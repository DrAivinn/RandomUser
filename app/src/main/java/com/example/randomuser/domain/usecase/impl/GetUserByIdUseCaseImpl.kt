package com.example.randomuser.domain.usecase.impl

import com.example.randomuser.domain.models.User
import com.example.randomuser.domain.repository.RandomUserRepository
import com.example.randomuser.domain.usecase.GetUserByIdUseCase
import jakarta.inject.Inject

class GetUserByIdUseCaseImpl @Inject constructor(val repository: RandomUserRepository) :
    GetUserByIdUseCase {
    override suspend fun invoke(id: Int): User {
        return repository.getUserById(id)
    }
}