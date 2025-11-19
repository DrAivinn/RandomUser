package com.example.randomuser.data.dto


data class RandomUserResponse(
    val results: List<UserDto>,
    val info: InfoDto
)

data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
    val email: String,
    val login: LoginDto,
    val dob: DateOfBirthDto,
    val registered: RegistrationDto,
    val phone: String,
    val cell: String,
    val id: IdentificationDto,
    val picture: PictureDto,
    val nat: String
)

data class NameDto(
    val title: String,
    val first: String,
    val last: String
)

data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates,
    val timezone: Timezone
)

data class StreetDto(
    val number: Int,
    val name: String
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class Timezone(
    val offset: String,
    val description: String
)

data class LoginDto(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

data class DateOfBirthDto(
    val date: String,
    val age: Int
)

data class RegistrationDto(
    val date: String,
    val age: Int
)

data class IdentificationDto(
    val name: String,
    val value: String
)

data class PictureDto(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class InfoDto(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)

