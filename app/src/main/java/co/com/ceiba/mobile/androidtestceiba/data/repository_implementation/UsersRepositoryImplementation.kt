package co.com.ceiba.mobile.androidtestceiba.data.repository_implementation

import co.com.ceiba.mobile.androidtestceiba.data.local.daos.PostDao
import co.com.ceiba.mobile.androidtestceiba.data.local.daos.UserDao
import co.com.ceiba.mobile.androidtestceiba.data.remote.CeibaApi
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.PostDto
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.UserDto
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.getPostsEntity
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.getUsersEntity
import co.com.ceiba.mobile.androidtestceiba.domain.models.Post
import co.com.ceiba.mobile.androidtestceiba.domain.models.User
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository
import javax.inject.Inject

/**
 * Implementacion real del repositorio que hace las transacciones entre el Api y la base de datos local
 * @param usersApi Instancia del Api que consume los servicios
 */
class UsersRepositoryImplementation @Inject constructor (
  private val usersApi : CeibaApi,
  private val usersDao : UserDao,
  private val postDao : PostDao
): UsersRepository {

  override suspend fun getUsers() : List<User> {
    refreshUser()
    return usersDao.getAllUsers()
  }

  private suspend fun refreshUser() {
    if(usersDao.tableIsEmpty() == 0) {
      val postsApi: List<PostDto> = usersApi.getAllPost()
      val usersApi: List<UserDto> = usersApi.getUsers()
      usersDao.insertUsers(usersApi.getUsersEntity())
      postDao.insertPosts(postsApi.getPostsEntity())
    }
  }

  override suspend fun getFilteredUsers(filterName: String) : List<User> {
    refreshUser()
    return usersDao.getFilteredUsers(filterName)
  }

  override suspend fun getPostByUserId(userId : Int) : List<Post> {
    return postDao.getPostByUserId(userId)
  }

}