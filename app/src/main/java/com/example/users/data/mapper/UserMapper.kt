package com.example.users.data.mapper

import com.example.users.data.local.entity.UserEntity
import com.example.users.domain.model.Coordinates
import com.example.users.domain.model.Dob
import com.example.users.domain.model.Id
import com.example.users.domain.model.Location
import com.example.users.domain.model.Name
import com.example.users.domain.model.Street
import com.example.users.domain.model.User
import com.example.users.domain.model.UserPicture
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun toEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id.value,
            title = user.name.title,
            firstName = user.name.first,
            lastName = user.name.second,
            email = user.email,
            phone = user.phone,
            cell = user.cell,
            pictureLarge = user.picture.large,
            pictureMedium = user.picture.medium,
            pictureThumbnail = user.picture.thumbnail,
            streetNumber = user.location.street.number,
            streetName = user.location.street.name,
            city = user.location.city,
            state = user.location.state,
            country = user.location.country,
            postcode = user.location.postcode,
            latitude = user.location.coordinates.latitude,
            longitude = user.location.coordinates.longitude,
            date = user.dob.date,
            age = user.dob.age,
            nat = user.nat,
            gender = user.gender
        )
    }

    fun toUser(entity: UserEntity): User {
        return User(
            id = Id(
                name = "",
                value = entity.id
            ),
            name = Name(
                title = entity.title,
                first = entity.firstName,
                second = entity.lastName
            ),
            email = entity.email,
            phone = entity.phone,
            cell = entity.cell,
            picture = UserPicture(
                large = entity.pictureLarge,
                medium = entity.pictureMedium,
                thumbnail = entity.pictureThumbnail
            ),
            location = Location(
                street = Street(
                    number = entity.streetNumber,
                    name = entity.streetName
                ),
                city = entity.city,
                state = entity.state,
                country = entity.country,
                postcode = entity.postcode,
                coordinates = Coordinates(
                    latitude = entity.latitude,
                    longitude = entity.longitude
                )
            ),
            dob = Dob(
                date = entity.date,
                age = entity.age
            ),
            nat = entity.nat,
            gender = entity.gender
        )
    }
}