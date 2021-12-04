package co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_users

import co.com.ceiba.mobile.androidtestceiba.common.Resource
import co.com.ceiba.mobile.androidtestceiba.domain.models.UsersEntity
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import retrofit2.HttpException

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
  suspend operator fun invoke(filterName : String? = null) : Flow<Resource<List<UsersEntity>>> = flow {
    try {
      emit(Resource.Loading<List<UsersEntity>>())
      val users = when(filterName) {
        null -> repository.getUsers()
        else -> repository.getFilteredUsers(filterName)
      }.flatMapConcat { it.asFlow() }.toList()
      emit(Resource.Success<List<UsersEntity>>(users))
    } catch( e: HttpException) {
      emit(Resource.Error<List<UsersEntity>>(e.localizedMessage ?: "ERROR DE RED INESPERADO"))
    } catch(e: IOException) {
      emit(Resource.Error<List<UsersEntity>>("REVISA LA CONEXION A INTERNET"))
    }
  }
}