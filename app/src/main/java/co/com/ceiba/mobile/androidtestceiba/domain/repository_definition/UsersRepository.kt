package co.com.ceiba.mobile.androidtestceiba.domain.repository_definition

import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.PostDto
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.UserDto

/**
 * Definición de los métodos que estructuraran el repositorio y separación de la implementación para
 * que al realizar las pruebas unitarias se pueda simular de una manera mas desacoplada
 */
interface UsersRepository {
  /**
   * Método que hace la consulta de la lista de usuarios
   */
  suspend fun getUsers(): List<UserDto>

  /**
   * Metodo que hace la consulta al servicio de los post corrspondientes al usuarios especificado mediante el Id
   */
  suspend fun getPostByUserId(userId: Int): List<PostDto>
}