package com.example.randomuser.domain.usecase

import com.example.randomuser.domain.models.User

interface GetRandomUserUseCase {
    suspend operator fun invoke(gender: String, nationality: String): User
}