package com.example.users.domain.model

data class User(
    val name: Name,
    val location: Location,
    val email: String,
    val phone: String,
    val cell: String,
    val picture: UserPicture,
    val gender: String,
    val dob: Dob,
    val nat: String,
    val id: Id
)