package co.com.ceiba.mobile.androidtestceiba.data.remote.dto

import co.com.ceiba.mobile.androidtestceiba.domain.models.User

/**
 * Modelo de datos que llega del API
 */
data class UserDto(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

/**
 * Función que transforma el objeto proveniente del api en un objeto de tipo entity
 */
fun List<UserDto>.getUsersEntity(): List<User> {
    return map { userDto ->
        User(
            userId = userDto.id,
            userName = userDto.name,
            userPhone = userDto.phone,
            userEmail = userDto.email
        )
    }
}