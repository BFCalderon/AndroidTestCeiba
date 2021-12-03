package co.com.ceiba.mobile.androidtestceiba.data.repository_implementation

import co.com.ceiba.mobile.androidtestceiba.data.remote.CeibaApi
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.PostDto
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.UserDto
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository

/**
 * Implementacion real del repositorio que hace las transacciones entre el Api y la base de datos local
 * @param usersApi Instancia del Api que consume los servicios
 */
class UsersRepositoryImplementation(
  private val usersApi : CeibaApi
): UsersRepository {

  override suspend fun getUsers() : List<UserDto> {
    return usersApi.getUsers()
  }

  override suspend fun getPostByUserId(userId : Int) : List<PostDto> {
    return usersApi.getPostUserById(userId)
  }

}