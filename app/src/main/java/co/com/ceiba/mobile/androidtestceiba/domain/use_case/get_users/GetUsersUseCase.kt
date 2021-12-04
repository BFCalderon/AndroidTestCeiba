package co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_users

import co.com.ceiba.mobile.androidtestceiba.domain.models.UsersEntity
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso para obtener el listado de usuarios en la BD
 * @param repository Instancia del repositorio
 */
class GetUsersUseCase(
  private val repository : UsersRepository
) {

  /**
   * Operador que ejecuta la consulta de los usuarios segun las validaciones
   * @param filterName String que sirve de filtro para consultar los usuarios
   */
  suspend operator fun invoke(filterName : String? = null) : Flow<List<UsersEntity>> {
    return when(filterName) {
      null -> repository.getUsers()
      else -> repository.getFilteredUsers(filterName)
    }
  }
}