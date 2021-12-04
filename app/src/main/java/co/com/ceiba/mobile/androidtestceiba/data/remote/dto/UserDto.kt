package co.com.ceiba.mobile.androidtestceiba.data.remote.dto

import co.com.ceiba.mobile.androidtestceiba.domain.models.UsersEntity

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
fun List<UserDto>.getUsersEntity(): List<UsersEntity> {
    return map { userDto ->
        UsersEntity(
            userId = userDto.id, userName = userDto.name, userEmail = userDto.email
        )
    }
}