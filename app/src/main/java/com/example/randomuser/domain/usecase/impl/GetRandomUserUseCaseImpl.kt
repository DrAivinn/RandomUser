package com.example.randomuser.domain.usecase.impl

import com.example.randomuser.domain.models.User
import com.example.randomuser.domain.repository.RandomUserRepository
import com.example.randomuser.domain.usecase.GetRandomUserUseCase
import jakarta.inject.Inject

class GetRandomUserUseCaseImpl @Inject constructor(private val repository: RandomUserRepository) :
    GetRandomUserUseCase {
    override suspend fun invoke(
        gender: String,
        nationality: String
    ): User {
        return repository.getRandomUser(gender, nationality)
    }
}