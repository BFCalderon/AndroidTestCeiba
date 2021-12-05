package co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_users

import co.com.ceiba.mobile.androidtestceiba.data.repository_implementation.UsersRepositoryFakeImplementationTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {
  private lateinit var getUsersUseCase : GetUsersUseCase
  private lateinit var fakeRepository: UsersRepositoryFakeImplementationTest

  @Before
  fun setUp(){
    fakeRepository = UsersRepositoryFakeImplementationTest()
    getUsersUseCase = GetUsersUseCase(fakeRepository)
  }

  @Test
  fun `prueba para obtener todos los usuarios`() = runBlockingTest {
      val allUsers = getUsersUseCase()
    Assert.assertEquals(3, allUsers.first().data?.size)
  }

  @Test
  fun `prueba para obtener los usuarios mediante el filtro`() = runBlockingTest {
    val filterUsers = getUsersUseCase("El")
    Assert.assertEquals(1, filterUsers.first().data?.size)
  }
}