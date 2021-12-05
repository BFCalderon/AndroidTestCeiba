package co.com.ceiba.mobile.androidtestceiba.data.repository_implementation

import co.com.ceiba.mobile.androidtestceiba.data.local.daos.PostDao
import co.com.ceiba.mobile.androidtestceiba.data.local.daos.UserDao
import co.com.ceiba.mobile.androidtestceiba.data.remote.CeibaApi
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.PostDto
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.UserDto
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.getPostsEntity
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.getUsersEntity
import co.com.ceiba.mobile.androidtestceiba.domain.models.PostsEntity
import co.com.ceiba.mobile.androidtestceiba.domain.models.UsersEntity
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count

/**
 * Implementacion real del repositorio que hace las transacciones entre el Api y la base de datos local
 * @param usersApi Instancia del Api que consume los servicios
 */
class UsersRepositoryImplementation @Inject constructor (
  private val usersApi : CeibaApi,
  private val usersDao : UserDao,
  private val postDao : PostDao
): UsersRepository {

  override suspend fun getUsers() : Flow<List<UsersEntity>> {
    val users = usersDao.getAllUsers()
    if(users.count() == 0) {
      val usersApi: List<UserDto> = usersApi.getUsers()
      usersDao.insertUsers(usersApi.getUsersEntity())
    }
    return users
  }

  override suspend fun getFilteredUsers(filterName: String) : Flow<List<UsersEntity>> {
    return usersDao.getFilteredUsers(filterName)
  }

  override suspend fun getPostByUserId(userId : Int) : Flow<List<PostsEntity>> {
    val postByUserId = postDao.getPostByUserId(userId)
    if(postByUserId.count() == 0) {
      val postsApi: List<PostDto> = usersApi.getPostUserById(userId)
      postDao.insertPosts(postsApi.getPostsEntity())
    }
    return postByUserId
  }

}