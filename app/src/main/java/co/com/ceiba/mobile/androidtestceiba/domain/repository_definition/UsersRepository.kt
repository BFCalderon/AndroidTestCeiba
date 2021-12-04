package co.com.ceiba.mobile.androidtestceiba.domain.repository_definition

import co.com.ceiba.mobile.androidtestceiba.domain.models.PostsEntity
import co.com.ceiba.mobile.androidtestceiba.domain.models.UsersEntity
import kotlinx.coroutines.flow.Flow

/**
 * Definición de los métodos que estructuraran el repositorio y separación de la implementación para
 * que al realizar las pruebas unitarias se pueda simular de una manera mas desacoplada
 */
interface UsersRepository {
  /**
   * Método que hace la consulta de la lista de usuarios
   */
  suspend fun getUsers(): Flow<List<UsersEntity>>

  /**
   * Método que hace la consulta de la lista de usuarios
   */
  suspend fun getFilteredUsers(filterName: String): Flow<List<UsersEntity>>

  /**
   * Metodo que hace la consulta al servicio de los post corrspondientes al usuarios especificado mediante el Id
   */
  suspend fun getPostByUserId(userId: Int): Flow<List<PostsEntity>>
}