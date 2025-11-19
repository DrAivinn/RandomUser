package com.example.randomuser.domain.usecase

import com.example.randomuser.domain.models.User

interface GetAllUsersUseCase {
    suspend operator fun invoke(): List<User>
}