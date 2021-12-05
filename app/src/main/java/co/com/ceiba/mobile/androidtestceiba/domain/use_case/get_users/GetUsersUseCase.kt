package co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_users

import co.com.ceiba.mobile.androidtestceiba.common.Resource
import co.com.ceiba.mobile.androidtestceiba.data.repository_implementation.UsersRepositoryImplementation
import co.com.ceiba.mobile.androidtestceiba.domain.models.User
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

/**
 * Caso de uso para obtener el listado de usuarios en la BD
 * @param repository Instancia del repositorio
 */
class GetUsersUseCase @Inject constructor(
  private val repository : UsersRepositoryImplementation
) {

  /**
   * Operador que ejecuta la consulta de los usuarios segun las validaciones
   * @param filterName String que sirve de filtro para consultar los usuarios
   */
  operator fun invoke(filterName : String? = null) : Flow<Resource<List<User>>> = flow {
    try {
      emit(Resource.Loading<List<User>>())
      val users = when(filterName) {
        null -> repository.getUsers()
        else -> repository.getFilteredUsers(filterName)
      }
      emit(Resource.Success<List<User>>(users))
    } catch( e: HttpException) {
      emit(Resource.Error<List<User>>(e.localizedMessage ?: "ERROR DE RED INESPERADO"))
    } catch(e: IOException) {
      emit(Resource.Error<List<User>>("REVISA LA CONEXION A INTERNET"))
    }
  }
}