package com.example.randomuser.domain.usecase

import com.example.randomuser.domain.models.User

interface GetUserByIdUseCase {
    suspend operator fun invoke(id: Int): User
}