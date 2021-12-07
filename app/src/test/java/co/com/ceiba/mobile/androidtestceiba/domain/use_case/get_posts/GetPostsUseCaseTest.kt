package co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_posts

import co.com.ceiba.mobile.androidtestceiba.data.repository_implementation.UsersRepositoryFakeImplementationTest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPostsUseCaseTest {

  private lateinit var getPostUseCase : GetPostsUseCase
  private lateinit var fakeRepository: UsersRepositoryFakeImplementationTest

  @Before
  fun setUp(){
    fakeRepository = UsersRepositoryFakeImplementationTest()
    getPostUseCase = GetPostsUseCase(fakeRepository)
  }
  @Test
  @Throws(Exception::class)
  fun `prueba para obtener los post filtrados por usuario`() = runBlocking {
    val filterUsers = getPostUseCase(3)
    assertEquals(2, filterUsers.toList().size)
  }
}