package co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_posts

import co.com.ceiba.mobile.androidtestceiba.domain.models.PostsEntity
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository
import kotlinx.coroutines.flow.Flow

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
  suspend operator fun invoke(userId : Int) : Flow<List<PostsEntity>> {
    return repository.getPostByUserId(userId)
  }
}