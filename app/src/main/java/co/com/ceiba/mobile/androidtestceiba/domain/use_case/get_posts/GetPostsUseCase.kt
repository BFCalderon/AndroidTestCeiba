package co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_posts

import co.com.ceiba.mobile.androidtestceiba.common.Resource
import co.com.ceiba.mobile.androidtestceiba.domain.models.Post
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

/**
 * Caso de uso que trae y valida los posts correspondientes a un usuario
 * @param repository Instancia del repositorio
 */
class GetPostsUseCase @Inject constructor(
  private val repository : UsersRepository
) {

  /**
   * Operador que ejecuta la consulta de los posts asociados a un usuario
   * @param userId Id del usuario que se desea consultar
   */
  operator fun invoke(userId : Int) : Flow<Resource<List<Post>>> = flow {
    try {
      emit(Resource.Loading<List<Post>>())
      val posts = repository.getPostByUserId(userId)
      emit(Resource.Success<List<Post>>(posts))
    } catch(e: HttpException){
      emit(Resource.Error<List<Post>>(e.localizedMessage ?: "ERROR DE RED INESPERADO"))
    } catch(e: IOException){
      emit(Resource.Error<List<Post>>("REVISA LA CONEXION A INTERNET"))
    }
  }
}