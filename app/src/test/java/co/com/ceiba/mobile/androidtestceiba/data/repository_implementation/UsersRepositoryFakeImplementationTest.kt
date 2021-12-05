package co.com.ceiba.mobile.androidtestceiba.data.repository_implementation

import co.com.ceiba.mobile.androidtestceiba.domain.models.Post
import co.com.ceiba.mobile.androidtestceiba.domain.models.User
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository

class UsersRepositoryFakeImplementationTest : UsersRepository {

  private val roomUsers: MutableList<User> = mutableListOf(
    User(1,"Elsa","123","ebastilla@hotmail.com"),
    User(2,"Sofia","456","scalderon@hotmail.com"),
    User(3,"Felipe","789","afelipe@hotmail.com")
  )
  private val roomPosts: MutableList<Post> = mutableListOf(
    Post(1,1,"Camino", "Largas Caminatas"),
    Post(2,1,"Cielo", "El cielo azul"),
    Post(3,2,"Prado", "Los prados del ensueño"),
    Post(4,2,"Moto", "La moto deportiva"),
    Post(5,3,"Ciudad", "Los grandes rascacielos"),
    Post(6,3,"Universo", "La infinidad del universo")
  )

  override suspend fun getUsers() : List<User> {
    return roomUsers
  }

  override suspend fun getFilteredUsers(filterName : String) : List<User> {
    val filterUserList: MutableList<User> = mutableListOf()
    roomUsers.forEach { user->
      if(user.userName.split(filterName).isNotEmpty()) {
        filterUserList.add(user)
      }
    }
    return filterUserList
  }

  override suspend fun getPostByUserId(userId : Int) : List<Post> {
    return roomPosts
  }

}