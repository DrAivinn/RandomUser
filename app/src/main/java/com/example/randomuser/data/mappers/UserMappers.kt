package com.example.randomuser.data.mappers

import com.example.randomuser.data.db.UserEntity
import com.example.randomuser.data.dto.UserDto
import com.example.randomuser.domain.models.User


// DTO -> Domain
fun UserDto.toDomain() = User(
    firstname = name.first,
    lastName = name.last,
    age = dob.age,
    dateOfBirth = dob.date,
    phone = phone,
    gender = gender,
    picture = picture.large
)

// Entity -> Domain
fun UserEntity.toDomain() = User(
    id = id,
    firstname = firstname,
    lastName = lastName,
    age = age,
    dateOfBirth = dateOfBirth,
    phone = phone,
    gender = gender,
    picture = picture
)

// Domain -> Entity
fun User.toEntity() = UserEntity(
    id = 0,
    firstname = firstname,
    lastName = lastName,
    age = age,
    dateOfBirth = dateOfBirth,
    phone = phone,
    gender = gender,
    picture = picture
)