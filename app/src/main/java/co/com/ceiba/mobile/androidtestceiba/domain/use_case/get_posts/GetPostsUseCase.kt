package co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_posts

import co.com.ceiba.mobile.androidtestceiba.common.Resource
import co.com.ceiba.mobile.androidtestceiba.domain.models.PostsEntity
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import retrofit2.HttpException

/**
 * Caso de uso que trae y valida los posts correspondientes a un usuario
 * @param repository Instancia del repositorio
 */
class GetPostsUseCase(
  private val repository : UsersRepository
) {

  /**
   * Operador que ejecuta la consulta de los posts asociados a un usuario
   * @param userId Id del usuario que se desea consultar
   */
  suspend operator fun invoke(userId : Int) : Flow<Resource<List<PostsEntity>>> = flow {
    try {
      emit(Resource.Loading<List<PostsEntity>>())
      val posts = repository.getPostByUserId(userId).flatMapConcat { it.asFlow() }.toList()
      emit(Resource.Success<List<PostsEntity>>(posts))
    } catch(e: HttpException){
      emit(Resource.Error<List<PostsEntity>>(e.localizedMessage ?: "ERROR DE RED INESPERADO"))
    } catch(e: IOException){
      emit(Resource.Error<List<PostsEntity>>("REVISA LA CONEXION A INTERNET"))
    }
  }
}