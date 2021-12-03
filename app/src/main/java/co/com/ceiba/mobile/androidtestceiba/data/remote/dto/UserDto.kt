package co.com.ceiba.mobile.androidtestceiba.data.remote.dto

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