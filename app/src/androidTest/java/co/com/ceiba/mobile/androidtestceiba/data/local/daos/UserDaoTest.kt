package co.com.ceiba.mobile.androidtestceiba.data.local.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import co.com.ceiba.mobile.androidtestceiba.data.local.database.CeibaDatabase
import co.com.ceiba.mobile.androidtestceiba.domain.models.User
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Prueba unitaria para comprobar el funcionamiento de UserDao
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest: TestCase() {

  private lateinit var database: CeibaDatabase
  private lateinit var userDao : UserDao
  private var roomUsers: MutableList<User> = mutableListOf(
    User(1,"Elsa","123","ebastilla@hotmail.com"),
    User(2,"Sofia","456","scalderon@hotmail.com"),
    User(3,"Elsa Bastilla","987","ebastilla2@hotmail.com"),
    User(4,"Felipe","789","afelipe@hotmail.com")
  )

  @Before
  public override fun setUp() {
    super.setUp()
    database = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(), CeibaDatabase::class.java
    ).allowMainThreadQueries().build()
    userDao = database.userDao
  }

  @After
  @Throws(IOException::class)
  fun teardown(){
    database.close()
  }

  /**
   * Prueba unitaria para la función que inserta los usuarios
   */
  @Test
  fun insertUsers() = runBlocking {
    userDao.insertUsers(roomUsers)
    val allUsers = userDao.getAllUsers()
    assertEquals(roomUsers, allUsers)
  }

  /**
   * Prueba unitaria para la función que filtra los usuario por palabra clave
   */
  @Test
  fun getFilteredUsers() = runBlocking {
    val filterValue = "lsa"
    val filteredUsersTestList = roomUsers.filter { user->
      user.userName.contains(filterValue)
    }
    userDao.insertUsers(roomUsers)
    val filteredUsersBd = userDao.getFilteredUsers(filterValue)
    assertEquals(filteredUsersTestList, filteredUsersBd)
  }

  /**
   * Prueba unitaria para la función que trae todos los usuarios de la  BD
   */
  @Test
  fun getAllUsers() = runBlocking {
    userDao.insertUsers(roomUsers)
    val savedUsers = userDao.getAllUsers()
    assertEquals(roomUsers.size, savedUsers.size)
  }

  /**
   * Prueba unitaria para la función que valida si la tabla está vacía
   */
  @Test
  fun tableIsEmpty() = runBlocking {
    userDao.insertUsers(roomUsers)
    val savedSize = userDao.getUsersQuantity()
    assertEquals(roomUsers.size, savedSize)
  }
}