package co.com.ceiba.mobile.androidtestceiba.data.local.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import co.com.ceiba.mobile.androidtestceiba.data.local.database.CeibaDatabase
import co.com.ceiba.mobile.androidtestceiba.domain.models.User
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

  private lateinit var database: CeibaDatabase
  private lateinit var userDao : UserDao
  private var roomUsers: List<User> = listOf(
    User(1,"Elsa","123","ebastilla@hotmail.com"),
    User(2,"Sofia","456","scalderon@hotmail.com"),
    User(3,"Felipe","789","afelipe@hotmail.com")
  )

  @Before
  fun setup() {
    database = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(), CeibaDatabase::class.java
    ).allowMainThreadQueries().build()
    userDao = database.userDao
  }

  @After
  fun teardown(){
    database.close()
  }

  @Test
  fun insertUsers() = runBlockingTest {
    userDao.insertUsers(roomUsers)
    roomUsers = mutableListOf()
    val allUsers = userDao.getAllUsers()
    Assert.assertEquals(roomUsers.size, allUsers.size)
  }
}